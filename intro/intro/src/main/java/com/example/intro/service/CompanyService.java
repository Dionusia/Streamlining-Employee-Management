package com.example.intro.service;

import com.example.intro.dto.CompanyDTO;
import com.example.intro.entity.Company;
import com.example.intro.entity.Employee;
import com.example.intro.repository.CompanyRepository;
import com.example.intro.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CompanyDTO saveCompany(CompanyDTO companyDTO){
        companyRepository.save(modelMapper.map(companyDTO, Company.class));
        return companyDTO;
    }

    @Transactional(readOnly = true)
    public List<CompanyDTO> getAllCompanies(){
        List<Company> companyList = companyRepository.findAll();
        return modelMapper.map(companyList, new TypeToken<List<CompanyDTO>>(){}.getType());
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO){
        companyRepository.save(modelMapper.map(companyDTO,Company.class));
        return companyDTO;
    }

    public boolean deleteCompany(CompanyDTO companyDTO){
        companyRepository.delete(modelMapper.map(companyDTO, Company.class));
        return true;
    }

    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public Double getTotalSalariesForCompany(Long companyId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(Math.toIntExact(companyId));
        List<Employee> employees = employeeOptional.map(Collections::singletonList).orElse(Collections.emptyList());
        double totalSalaries = 0.0;
        for (Employee employee : employees) {
            totalSalaries += employee.getSalary().doubleValue();
        }
        return totalSalaries;
    }
}