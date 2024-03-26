package com.example.intro.controller;

import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.entity.Company;
import com.example.intro.entity.Request;
import com.example.intro.entity.Product;
import com.example.intro.repository.CompanyRepository;
import com.example.intro.service.EmployeeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/employee_product")
public class EmployeeProductController {

    @Autowired
    private EmployeeProductService employee_productService;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping()
    public List<EmployeeProductDTO> getEmployeeProducts(){
        return employee_productService.getAllEmployeeProducts();
    }

    @PostMapping()
    public EmployeeProductDTO saveEmployeeProduct(@RequestBody EmployeeProductDTO employee_productDTO){
        return employee_productService.saveEmployeeProduct(employee_productDTO);
    }

    @PutMapping()
    public EmployeeProductDTO updateEmployeeProduct(@RequestBody EmployeeProductDTO employee_productDTO){
        return employee_productService.updateEmployeeProduct(employee_productDTO);
    }

    @DeleteMapping()
    public boolean deleteEmployeeProduct(@RequestBody EmployeeProductDTO employee_productDTO){
        return employee_productService.deleteEmployeeProduct(employee_productDTO);
    }

    @GetMapping("/employee-products/{companyId}")
    public Map<String, List<Product>> getCompanyProducts(Request request){
        List<Company> company = companyRepository.findById(request.getCompanyId());
        if (company.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id " + request.getCompanyId());
        }
        return employee_productService.getAllProductsForCompany(request.getCompanyId());
    }
}
