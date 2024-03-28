package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.ExtendedVacationRequestDTO;
import com.example.intro.dto.VacationRequestDTO;
import com.example.intro.entity.VacationRequest;
import com.example.intro.entity.VacationStatus;
import com.example.intro.repository.VacationRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@Transactional
@Log4j2
public class VacationRequestService {

    @Autowired
    private VacationRequestRepository vacationrequestRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;

    public VacationRequestDTO saveVacationRequest(VacationRequestDTO vacationrequestDTO){
        vacationrequestRepository.save(modelMapper.map(vacationrequestDTO, VacationRequest.class));
        return vacationrequestDTO;
    }

    public List<VacationRequestDTO> getVacationRequests(){
        List<VacationRequest> vacationrequestList = vacationrequestRepository.findAll();
        return modelMapper.map(vacationrequestList, new TypeToken<List<VacationRequestDTO>>(){}.getType());
    }

    public VacationRequestDTO updateVacationRequest(VacationRequestDTO vacationrequestDTO){
        vacationrequestRepository.save(modelMapper.map(vacationrequestDTO,VacationRequest.class));
        return vacationrequestDTO;
    }

    public boolean deleteVacationRequest(VacationRequestDTO vacationrequestDTO){
        vacationrequestRepository.delete(modelMapper.map(vacationrequestDTO, VacationRequest.class));
        return true;
    }

    /**
     * Submits a vacation request.
     *
     * @param vacationRequestDTO The ExtendedVacationRequestDTO object representing the extended vacation request.
     * @return VacationRequestDTO representing the submitted vacation request.
     * @throws IllegalArgumentException if the employee does not have enough remaining days for the requested vacation.
     */
    public VacationRequestDTO submitVacationRequest(ExtendedVacationRequestDTO vacationRequestDTO) {
        log.debug("Submitting vacation request...");
        // Calculate working days
        int days = calculateWorkingDays(vacationRequestDTO.getStartDate(), vacationRequestDTO.getEndDate(), vacationRequestDTO.getHoliday());
        //retrieve remaining vacation days
        Integer remainingDays = employeeService.getRemainingVacationDays(vacationRequestDTO.getEmployee().getId().intValue());
        if (days > remainingDays) {
            throw new IllegalArgumentException("Does not have enough remaining days");
        }
        //create the request and save
        VacationRequest vacationRequest = createRequest(vacationRequestDTO, days);
        return modelMapper.map(vacationRequest, VacationRequestDTO.class);
    }

    /**
     * Updates the status of a vacation request based on the provided request DTO.
     *
     * @param requestDTO The DTO containing the updated status information.
     * @return The updated vacation request DTO.
     * @throws IllegalArgumentException If the provided request status is invalid or the vacation request cannot be found.
     */
    public VacationRequestDTO updateVacationRequestStatus(VacationRequestDTO requestDTO) {
        log.debug("Updating vacation request status...");
        Optional<VacationRequest> existingRequestOptional = vacationrequestRepository.findById(Math.toIntExact(requestDTO.getId()));

        if (existingRequestOptional.isPresent()) {
            VacationRequest existingRequest = existingRequestOptional.get();

            if (!existingRequest.getStatus().equals(VacationStatus.PENDING)) {
                throw new IllegalArgumentException("Status cannot be changed. Current status: " + existingRequest.getStatus());
            }

            Function<VacationRequest, VacationRequestDTO> action = actionStatus(requestDTO.getStatus());
            return action.apply(existingRequest);

        } else {
            throw new IllegalArgumentException("Vacation request with ID " + requestDTO.getId() + " not found");
        }
    }

    /**
     * Determines the action to be performed based on the provided status.
     *
     * @param status The status of the vacation request.
     * @return The function representing the action to be performed.
     * @throws IllegalArgumentException If the provided status is invalid.
     */
    private Function<VacationRequest, VacationRequestDTO> actionStatus(VacationStatus status) {
        Map<VacationStatus, Function<VacationRequest, VacationRequestDTO>> handle = new HashMap<>();
        handle.put(VacationStatus.APPROVED, this::handleApproved);
        handle.put(VacationStatus.REJECTED, this::handleRejected);

        Function<VacationRequest, VacationRequestDTO> action = handle.get(status);
        return Optional.ofNullable(handle.get(status))
                .orElseThrow(() -> new IllegalArgumentException("Invalid vacation request status: " + status));
    }

    /**
     * Handles the approval of a vacation request.
     *
     * @param existingRequest The existing vacation request to be approved.
     * @return The updated vacation request DTO.
     * @throws IllegalArgumentException If the employee does not have enough vacation days.
     */
    private VacationRequestDTO handleApproved(VacationRequest existingRequest) {
        Integer remainingDays = employeeService.getRemainingVacationDays((int) (long) existingRequest.getEmployee().getId());
        if (existingRequest.getDays() > remainingDays) {
            throw new IllegalArgumentException("Employee does not have enough days");
        }

        EmployeeDTO employee = employeeService.getEmployeeById((int) (long) existingRequest.getEmployee().getId());
        int updatedVacationDays = employee.getVacationDays() - existingRequest.getDays();
        employee.setVacationDays(updatedVacationDays);
        employeeService.updateEmployee(employee);

        existingRequest.setStatus(VacationStatus.APPROVED);
        vacationrequestRepository.save(existingRequest);
        return modelMapper.map(existingRequest, VacationRequestDTO.class);
    }

    /**
     * Handles the rejection of a vacation request.
     *
     * @param existingRequest The existing vacation request to be rejected.
     * @return The updated vacation request DTO.
     */
    private VacationRequestDTO handleRejected(VacationRequest existingRequest) {
        existingRequest.setStatus(VacationStatus.REJECTED);
        vacationrequestRepository.save(existingRequest);
        return modelMapper.map(existingRequest, VacationRequestDTO.class);
    }

    private int calculateWorkingDays(Date startDate, Date endDate, int holidays) {
        // Check for null values
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        //convert to localDate
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int workingDays = 0;
        LocalDate date = localStartDate;
        while (!date.isAfter(localEndDate)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDays++;
            }
            date = date.plusDays(1);
        }
        //subtract holiday
        return workingDays - holidays;
    }

    private VacationRequest createRequest(VacationRequestDTO vacationRequestDTO, int days) {
        EmployeeDTO employee = employeeService.getEmployeeById(vacationRequestDTO.getEmployee().getId().intValue());
        vacationRequestDTO.setStatus(VacationStatus.PENDING);
        vacationRequestDTO.setDays(days);
        vacationRequestDTO.setEmployee(employee);
        VacationRequest vacationRequest = modelMapper.map(vacationRequestDTO, VacationRequest.class);
        vacationrequestRepository.save(vacationRequest);
        return vacationRequest;
    }
}