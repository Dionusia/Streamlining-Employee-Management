<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
<<<<<<< HEAD:intro/src/main/java/com/example/intro/service/VacationRequestService.java
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.ExtendedVacationRequestDTO;
import com.example.intro.dto.VacationRequestDTO;
import com.example.intro.entity.VacationRequest;
import com.example.intro.entity.VacationStatus;
import com.example.intro.repository.VacationRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
<<<<<<< HEAD
<<<<<<< HEAD
import lombok.extern.log4j.Log4j2;
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
<<<<<<< HEAD
<<<<<<< HEAD
@Log4j2
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
<<<<<<< HEAD
<<<<<<< HEAD
        log.debug("Submitting vacation request...");
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
<<<<<<< HEAD
<<<<<<< HEAD
        log.debug("Vacation request submitted successfully.");
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
        return modelMapper.map(vacationRequest, VacationRequestDTO.class);
    }

    /**
     * Updates the status of a vacation request.
     *
     * @param requestDTO The VacationRequestDTO object representing the vacation request with updated status.
     * @return VacationRequestDTO representing the updated vacation request.
     * @throws EntityNotFoundException if the vacation request is not found.
     * @throws IllegalArgumentException if the status cannot be changed or the provided status is invalid.
     */
    public VacationRequestDTO updateVacationRequestStatus(VacationRequestDTO requestDTO) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
<<<<<<< HEAD
<<<<<<< HEAD

        log.debug("Updating vacation request status...");
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
<<<<<<< HEAD
<<<<<<< HEAD
        log.debug("Vacation request status updated successfully.");
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
=======
>>>>>>> parent of ba7e421 (Revert "change path")
=======
>>>>>>> parent of ba7e421 (Revert "change path")
>>>>>>> parent of 368f17b (path):intro/src/main/java/com/example/intro/service/VacationRequestService.java
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

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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

        log.debug("Vacation request submitted successfully.");
        return modelMapper.map(vacationRequest, VacationRequestDTO.class);
    }

    /**
     * Updates the status of a vacation request.
     *
     * @param requestDTO The VacationRequestDTO object representing the vacation request with updated status.
     * @return VacationRequestDTO representing the updated vacation request.
     * @throws EntityNotFoundException if the vacation request is not found.
     * @throws IllegalArgumentException if the status cannot be changed or the provided status is invalid.
     */
    public VacationRequestDTO updateVacationRequestStatus(VacationRequestDTO requestDTO) {

        log.debug("Updating vacation request status...");
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

        log.debug("Vacation request status updated successfully.");
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
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
}
=======
}
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of ba7e421 (Revert "change path"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
=======
>>>>>>> parent of ba7e421 (Revert "change path")
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

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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

        log.debug("Vacation request submitted successfully.");
        return modelMapper.map(vacationRequest, VacationRequestDTO.class);
    }

    /**
     * Updates the status of a vacation request.
     *
     * @param requestDTO The VacationRequestDTO object representing the vacation request with updated status.
     * @return VacationRequestDTO representing the updated vacation request.
     * @throws EntityNotFoundException if the vacation request is not found.
     * @throws IllegalArgumentException if the status cannot be changed or the provided status is invalid.
     */
    public VacationRequestDTO updateVacationRequestStatus(VacationRequestDTO requestDTO) {

        log.debug("Updating vacation request status...");
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

        log.debug("Vacation request status updated successfully.");
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
<<<<<<< HEAD
>>>>>>> parent of 721cb0f (change path):intro/intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ba7e421 (Revert "change path")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ba7e421 (Revert "change path")
>>>>>>> parent of 368f17b (path):intro/src/main/java/com/example/intro/service/VacationRequestService.java
=======
>>>>>>> parent of ba7e421 (Revert "change path")
