package com.example.intro.service;

import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.entity.Employee;
import com.example.intro.entity.EmployeeProduct;
import com.example.intro.entity.Product;
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
     * Retrieves all products associated with employees of a specific company.
     *
     * @param companyId The ID of the company for which products are to be retrieved.
     * @return A map where the key is a string representation of an employee (name, surname, ID), and the value is a list of products associated with that employee.
     */
    public Map<String, List<Product>> getAllProductsForCompany(Long companyId) {
        //retrieve all employees for the given company ID
        Optional<Employee> employeeOptional = employeeRepository.findById(Math.toIntExact(companyId));
        List<Employee> employees = employeeOptional.map(Collections::singletonList).orElse(Collections.emptyList());
        //retrieve all products for these employees
        List<EmployeeProductDTO> employeeProducts = getAllEmployeeProducts();
        //map where the key is an employee and value a list of products
        Map<String, List<Product>> resultMap = new HashMap<>();
        for (Employee e : employees) {
            //build a key for the employee using name, surname, and ID
            String employeeKey = e.getName() + " " + e.getSurname() + " (" + e.getId() + ")";
            //initialize an empty list of products for this employee
            resultMap.put(employeeKey, new ArrayList<>());
            //iterate over employee products and add them to the result map
            for (EmployeeProductDTO p : employeeProducts) {
                //check if the employee ID matches and the employee name and surname are the same
                if (p.getEmployee().getId().equals(e.getId())) {
                    resultMap.get(employeeKey).add(p.getProduct());
                }
            }
        }
        return resultMap;
    }
}