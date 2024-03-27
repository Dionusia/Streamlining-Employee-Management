package com.example.intro.repository;

import com.example.intro.entity.Company;
import com.example.intro.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository <Company, Integer> {
    List<Company> findById(Long companyId);
}
