package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.EmployeeProduct;
import com.example.intro.entity.ProductsEmployee;
import com.example.intro.repository.EmployeeProductRepository;
import com.example.intro.repository.EmployeeRepository;
import com.example.intro.repository.ProductRepository;

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
public class EmployeeProductService {

    @Autowired
    private EmployeeProductRepository employeeproductRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeProductDTO saveEmployeeProduct(EmployeeProductDTO employeeproductDTO){
        employeeproductRepository.save(modelMapper.map(employeeproductDTO, EmployeeProduct.class));
        return employeeproductDTO;
    }

    @Transactional(readOnly = true)
    public List<EmployeeProductDTO> getAllEmployeeProducts(){
        List<EmployeeProduct> employeeproductsList = employeeproductRepository.findAll();
        return modelMapper.map(employeeproductsList, new TypeToken<List<EmployeeProductDTO>>(){}.getType());
    }
    public EmployeeProductDTO updateEmployeeProduct(EmployeeProductDTO employeeproductDTO){
        employeeproductRepository.save(modelMapper.map(employeeproductDTO,EmployeeProduct.class));
        return employeeproductDTO;
    }

    public boolean deleteEmployeeProduct(EmployeeProductDTO employeeproductDTO){
        employeeproductRepository.delete(modelMapper.map(employeeproductDTO, EmployeeProduct.class));
        return true;
    }

    /**
     * Retrieves all products associated with employees of a given company.
     *
     * @param companyId The ID of the company for which products are to be retrieved.
     * @return A map containing employees of the company as keys and lists of products associated with each employee as values.
     *         Each entry in the map represents an employee and their associated products.
     */
    public Map<ProductsEmployee, List<ProductDTO>> getAllProductsForCompany(Long companyId) {
        //retrieve all employees for the given company ID
        List<EmployeeDTO> employees = employeeService.getEmployeesByCompanyId(companyId);
        //retrieve all products for these employees
        List<EmployeeProductDTO> employeeProducts = getAllEmployeeProducts();

        //create a map to store employees and their products
        Map<ProductsEmployee, List<ProductDTO>> resultMap = new HashMap<>();

        //iterate over each employee
        for (EmployeeDTO e : employees) {
            ProductsEmployee et = new ProductsEmployee(e.getId(), e.getName(), e.getSurname());
            //create a list to store products for the current employee
            List<ProductDTO> productsForEmployee = new ArrayList<>();
            //iterate over all employee products to find products associated with the current employee
            for (EmployeeProductDTO p : employeeProducts) {
                if (e.getId().equals(p.getEmployee().getId())) {
                    //add the product to the list of products for the current employee
                    productsForEmployee.add(p.getProduct());
                }
            }
            //put the employee and their associated products into the result map
            resultMap.put(et, productsForEmployee);
        }
        return resultMap;
    }

}