package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.Employee;
import com.example.intro.entity.EmployeeProduct;
import com.example.intro.entity.Product;
import com.example.intro.repository.ProductRepository;
import jakarta.transaction.Transactional;
<<<<<<< HEAD
import lombok.extern.log4j.Log4j2;
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
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
<<<<<<< HEAD
@Log4j2
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO saveProduct(ProductDTO productDTO) {
        productRepository.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {
        }.getType());
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepository.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public boolean deleteProduct(ProductDTO productDTO) {
        productRepository.delete(modelMapper.map(productDTO, Product.class));
        return true;
    }
}
