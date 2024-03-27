<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/EmployeeService.java
=======
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/EmployeeService.java
<<<<<<< HEAD:intro/src/main/java/com/example/intro/service/EmployeeService.java
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/EmployeeService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/EmployeeService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
>>>>>>> parent of 368f17b (path):intro/src/main/java/com/example/intro/service/EmployeeService.java
package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.entity.Employee;
import com.example.intro.repository.EmployeeProductRepository;
import com.example.intro.repository.EmployeeRepository;
import com.example.intro.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Employee> employees = employeeRepository.findByCompanyId(companyId);
        return modelMapper.map(employees, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/EmployeeService.java
}
=======
}

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of ba7e421 (Revert "change path"):intro/src/main/java/com/example/intro/service/EmployeeService.java
=======
=======
>>>>>>> parent of ba7e421 (Revert "change path")
package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.Employee;
import com.example.intro.entity.EmployeeProduct;
import com.example.intro.entity.Product;
import com.example.intro.repository.EmployeeProductRepository;
import com.example.intro.repository.EmployeeRepository;
import com.example.intro.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        List<Employee> employees = employeeRepository.findByCompanyId(companyId);
        return modelMapper.map(employees, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }
}

<<<<<<< HEAD
>>>>>>> parent of 721cb0f (change path):intro/intro/src/main/java/com/example/intro/service/EmployeeService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/EmployeeService.java
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
>>>>>>> parent of 368f17b (path):intro/src/main/java/com/example/intro/service/EmployeeService.java
=======
>>>>>>> parent of ba7e421 (Revert "change path")
