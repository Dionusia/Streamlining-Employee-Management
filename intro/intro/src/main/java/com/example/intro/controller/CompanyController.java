package com.example.intro.controller;

import com.example.intro.dto.CompanyDTO;
import com.example.intro.entity.Company;
import com.example.intro.entity.Request;
import com.example.intro.repository.CompanyRepository;
import com.example.intro.service.CompanyService;
import com.example.intro.service.EmployeeService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public List<CompanyDTO> getCompany(){
        return companyService.getAllCompanies();
    }

    @PostMapping()
    public CompanyDTO saveCompany(@RequestBody CompanyDTO companyDTO){
        return companyService.saveCompany(companyDTO);
    }

    @PutMapping()
    public CompanyDTO updateCompany(@RequestBody CompanyDTO companyDTO){
        return companyService.updateCompany(companyDTO);
    }

    //delete by ID
    @DeleteMapping()
    public boolean deleteCompany(@RequestBody CompanyDTO companyDTO){
        return companyService.deleteCompany(companyDTO);
    }

    //bulk delete
    @DeleteMapping("/deleteAll")
    public void deleteAllCompanies() {
        companyService.deleteAllCompanies();
    }

    @GetMapping("/salaries/{companyId}")
    public ResponseEntity<Double> getTotalSalariesForCompany(Request request) {
        List<Company> company = companyRepository.findById(request.getCompanyId());
        if (company.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id " + request.getCompanyId());
        }

        Double totalSalaries = companyService.getTotalSalariesForCompany(request.getCompanyId());
        return ResponseEntity.ok(totalSalaries);
    }

}
