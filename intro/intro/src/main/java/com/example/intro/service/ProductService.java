package com.example.intro.service;

import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.Product;
import com.example.intro.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Log4j2

public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO saveProduct(ProductDTO productDTO) {
        productRepository.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    @Transactional(readOnly = true)
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