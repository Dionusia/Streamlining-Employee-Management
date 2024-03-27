package com.example.intro.repository;

import com.example.intro.entity.EmployeeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeProductRepository extends JpaRepository <EmployeeProduct, Integer>{
}
