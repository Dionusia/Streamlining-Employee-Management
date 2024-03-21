package com.example.intro.controller;


import com.example.intro.dto.ExtendedVacationRequestDTO;
import com.example.intro.dto.VacationRequestDTO;
import com.example.intro.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/vacation-request")
@CrossOrigin
public class VacationRequestController {
    @Autowired
    private VacationRequestService vacationrequestService;

    @GetMapping("/get")
    public List<VacationRequestDTO> getVacationRequests(){
        return vacationrequestService.getVacationRequests();
    }

    @PostMapping("/save")
    public VacationRequestDTO saveVacationRequest(@RequestBody VacationRequestDTO vacationrequestDTO){
        return vacationrequestService.saveVacationRequest(vacationrequestDTO);
    }

    @PutMapping("/update")
    public VacationRequestDTO updateVacationRequest(@RequestBody VacationRequestDTO vacationrequestDTO){
        return vacationrequestService.updateVacationRequest(vacationrequestDTO);
    }

    @DeleteMapping("/delete")
    public boolean deleteVacationRequest(@RequestBody VacationRequestDTO vacationrequestDTO){
        return vacationrequestService.deleteVacationRequest(vacationrequestDTO);
    }

    //ask for vacation
    @PostMapping("/submit-vacation-request")
    public VacationRequestDTO submitVacationRequest(@RequestBody ExtendedVacationRequestDTO requestDTO) {
        return vacationrequestService.submitVacationRequest(requestDTO);
    }
    @PutMapping("/update-vacation-requestStatus")
    public VacationRequestDTO updateVacationRequestStatus(@RequestBody VacationRequestDTO requestDTO) {
        return vacationrequestService.updateVacationRequestStatus(requestDTO);
    }
}
