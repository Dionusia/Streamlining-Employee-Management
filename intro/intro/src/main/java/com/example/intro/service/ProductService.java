<<<<<<< HEAD
<<<<<<< HEAD:intro/src/main/java/com/example/intro/service/ProductService.java
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/ProductService.java
<<<<<<< HEAD:intro/src/main/java/com/example/intro/service/ProductService.java
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/ProductService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/ProductService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/ProductService.java
<<<<<<< HEAD
<<<<<<< HEAD
import lombok.extern.log4j.Log4j2;
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/ProductService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/ProductService.java
<<<<<<< HEAD
<<<<<<< HEAD
@Log4j2
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/ProductService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
=======
>>>>>>> parent of ee5891e (Revert "change path folder")
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/ProductService.java
=======
=======
>>>>>>> parent of ba7e421 (Revert "change path")
=======
>>>>>>> parent of ba7e421 (Revert "change path")
package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.Employee;
import com.example.intro.entity.EmployeeProduct;
=======
package com.example.intro.service;

import com.example.intro.dto.ProductDTO;
>>>>>>> parent of d89e26a (path)
import com.example.intro.entity.Product;
import com.example.intro.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import java.util.List;
>>>>>>> parent of d89e26a (path)

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
<<<<<<< HEAD
}
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of ba7e421 (Revert "change path"):intro/src/main/java/com/example/intro/service/ProductService.java
=======
package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.Employee;
import com.example.intro.entity.EmployeeProduct;
import com.example.intro.entity.Product;
import com.example.intro.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
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
>>>>>>> parent of 721cb0f (change path):intro/intro/src/main/java/com/example/intro/service/ProductService.java
=======
>>>>>>> parent of 33d6af5 (Revert "change path folder"):intro/src/main/java/com/example/intro/service/ProductService.java
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
=======
package com.example.intro.service;

import com.example.intro.dto.EmployeeDTO;
import com.example.intro.dto.EmployeeProductDTO;
import com.example.intro.dto.ProductDTO;
import com.example.intro.entity.Employee;
import com.example.intro.entity.EmployeeProduct;
import com.example.intro.entity.Product;
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
>>>>>>> parent of 9f464f9 (change path folder):intro/intro/src/main/java/com/example/intro/service/ProductService.java
=======
}
>>>>>>> parent of d89e26a (path)
