package com.example.intro.controller;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.Employee;
import com.example.intro.entity.Product;
import com.example.intro.service.EmployeeService;
import com.example.intro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductService productService;

    @GetMapping("/get-employees")
    public List<EmployeeDTO> getEmployee(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/save-employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.saveEmployee(employeeDTO);
    }

    @PutMapping("/update-employee")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/delete-employee")
    public boolean deleteEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.deleteEmployee(employeeDTO);
    }

}
