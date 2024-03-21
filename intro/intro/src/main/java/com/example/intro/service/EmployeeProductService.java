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
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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

    public Map<String, List<Product>> getAllProductsForCompany(Long companyId) {
        //retrieve all employee for the given company ID
        List<Employee> employees = employeeRepository.findByCompanyId(companyId);
        //retrieve all products for these employees
        List<EmployeeProductDTO> employeeProducts = getAllEmployeeProducts();
        //a map where the key is an employee and value a list of products
        Map<String, List<Product>> resultMap = new HashMap<>();
        for (Employee e : employees) {
            for(EmployeeProductDTO p : employeeProducts){
                Product product = p.getProduct();
                String employeeKey = e.getName() + " " + e.getSurname();

                //if the key doesn't exist initialize empty list
                resultMap.putIfAbsent(employeeKey, new ArrayList<>());

                resultMap.get(employeeKey).add(product);
            }
        }
        return resultMap;
    }

}
