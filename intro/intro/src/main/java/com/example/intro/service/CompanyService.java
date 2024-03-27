package com.example.intro.service;

import com.example.intro.dto.CompanyDTO;
import com.example.intro.entity.Company;
import com.example.intro.entity.Employee;
import com.example.intro.repository.CompanyRepository;
import com.example.intro.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
<<<<<<< HEAD
<<<<<<< HEAD
import lombok.extern.log4j.Log4j2;
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
<<<<<<< HEAD
<<<<<<< HEAD
@Log4j2
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
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

    public Double getTotalSalariesForCompany(Long companyId) {
        List<Employee> employees = employeeRepository.findByCompanyId(companyId);

        double totalSalaries;
        totalSalaries = employees.stream()
                .mapToDouble(employee -> employee.getSalary().doubleValue())
                .sum();

        return totalSalaries;
    }
}
