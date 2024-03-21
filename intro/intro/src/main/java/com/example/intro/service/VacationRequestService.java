package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.ExtendedVacationRequestDTO;
import com.example.intro.dto.VacationRequestDTO;
import com.example.intro.entity.VacationRequest;
import com.example.intro.entity.VacationStatus;
import com.example.intro.repository.VacationRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
@Transactional
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

    public VacationRequestDTO submitVacationRequest(ExtendedVacationRequestDTO vacationRequestDTO) {
        long days = ChronoUnit.DAYS.between(vacationRequestDTO.getStartDate().toInstant(), vacationRequestDTO.getEndDate().toInstant()) + 1;
        days -= vacationRequestDTO.getHoliday(); // Subtract holidays

        //retrieve remaining vacation days
        Integer remainingDays = employeeService.getRemainingVacationDays(vacationRequestDTO.getEmployee().getId().intValue());

        if (days > remainingDays) {
            throw new IllegalArgumentException("Does not have enough remaining days");
        }

        vacationRequestDTO.setStatus(VacationStatus.PENDING);
        vacationRequestDTO.setDays((int) days);

        EmployeeDTO employee = employeeService.getEmployeeById(vacationRequestDTO.getEmployee().getId().intValue());
        vacationRequestDTO.setEmployee(employee);

        VacationRequest vacationRequest = modelMapper.map(vacationRequestDTO, VacationRequest.class);
        vacationrequestRepository.save(vacationRequest);

        return modelMapper.map(vacationRequest, VacationRequestDTO.class);
    }

    public VacationRequestDTO updateVacationRequestStatus(VacationRequestDTO requestDTO) {
        VacationRequest existingRequest = vacationrequestRepository.findById(requestDTO.getId());

        if (existingRequest == null) {
            throw new EntityNotFoundException("Vacation request not found with id: " + requestDTO.getId());
        }
        if (!existingRequest.getStatus().equals(VacationStatus.PENDING)) {
            throw new IllegalArgumentException("Status cannot be changed. Current status: " + existingRequest.getStatus());
        }

        String status = String.valueOf(requestDTO.getStatus());

        Map<String, Consumer<VacationRequest>> handle = new HashMap<>();
        handle.put(VacationStatus.APPROVED.name(), this::handleApproved);
        handle.put(VacationStatus.REJECTED.name(), this::handleRejected);

        Consumer<VacationRequest> action = handle.get(status);
        if (action != null) {
            action.accept(existingRequest);
        } else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        vacationrequestRepository.save(existingRequest);

        return modelMapper.map(existingRequest, VacationRequestDTO.class);
    }

    private void handleApproved(VacationRequest existingRequest) {
        Integer remainingDays = employeeService.getRemainingVacationDays((int) (long) existingRequest.getEmployee().getId());
        if (existingRequest.getDays() > remainingDays) {
            throw new IllegalArgumentException("Employee does not have enough days");
        }

        EmployeeDTO employee = employeeService.getEmployeeById((int) (long) existingRequest.getEmployee().getId());
        int updatedVacationDays = employee.getVacationDays() - existingRequest.getDays();
        employee.setVacationDays(updatedVacationDays);
        employeeService.updateEmployee(employee);

        existingRequest.setStatus(VacationStatus.APPROVED);
    }

    private void handleRejected(VacationRequest existingRequest) {
        existingRequest.setStatus(VacationStatus.REJECTED);
    }
}
