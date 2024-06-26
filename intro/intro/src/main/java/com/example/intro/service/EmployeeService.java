package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.entity.Employee;
import com.example.intro.repository.EmployeeProductRepository;
import com.example.intro.repository.EmployeeRepository;
import com.example.intro.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
@Log4j2
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private EmployeeProductRepository employeeProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
        return employeeDTO;
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        return modelMapper.map(employeeList, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }

    public EmployeeDTO getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO){
        employeeRepository.save(modelMapper.map(employeeDTO,Employee.class));
        return employeeDTO;
    }

    public boolean deleteEmployee(EmployeeDTO employeeDTO){
        employeeRepository.delete(modelMapper.map(employeeDTO, Employee.class));
        return true;
    }

    public Integer getRemainingVacationDays(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));

        return employee.getVacationDays();
    }

    public List<EmployeeDTO> getEmployeesByCompanyId(Long companyId) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        //retrieve all employees
        List<Employee> allEmployees = employeeRepository.findAll(); //employees exists
        //iterate over all employees and match if equals to companyId
        for (Employee employee : allEmployees) {
            if (employee.getCompany().getId().equals(companyId)) {
                employeeDTOs.add(modelMapper.map(employee, EmployeeDTO.class));
            }
        }
        return employeeDTOs;
    }
}