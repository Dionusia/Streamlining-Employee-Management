package com.example.intro.controller;

import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.Company;
import com.example.intro.entity.ProductsEmployee;
import com.example.intro.entity.Request;
import com.example.intro.repository.CompanyRepository;
import com.example.intro.service.EmployeeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/employee-product")
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

    //employee's products by company id
    @GetMapping("/{companyId}")
    public Map<ProductsEmployee, List<ProductDTO>> getCompanyProducts(Request request){
        Optional<Company> company = companyRepository.findById(Math.toIntExact(request.getCompanyId()));
        if (company.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id " + request.getCompanyId());
        }
        return employee_productService.getAllProductsForCompany(request.getCompanyId());
    }
}
