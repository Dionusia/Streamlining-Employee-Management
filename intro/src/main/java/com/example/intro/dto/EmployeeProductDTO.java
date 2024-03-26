package com.example.intro.dto;

import com.example.intro.entity.Employee;
import com.example.intro.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeProductDTO {

    private Long id;
    @JsonIgnoreProperties({"name", "surname", "email", "startDate", "vacationDays", "salary", "employmentType", "company"})
    private Employee employee;
    private Product product;
}
