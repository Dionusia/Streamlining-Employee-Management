package com.example.intro.dto;

import com.example.intro.entity.Company;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Date startDate;
    private Integer vacationDays;
    private BigDecimal salary;
    private String employmentType;
    @JsonIgnoreProperties({"name", "address", "phone"})
    private CompanyDTO company;
}
