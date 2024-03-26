package com.example.intro.repository;

import com.example.intro.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeRepository extends JpaRepository <Employee, Integer> {
    List<Employee> findByCompanyId(Long companyId);
}
