package com.example.intro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BonusDTO {

    private Long id;
    @JsonIgnoreProperties({"name", "surname", "email", "startDate", "vacationDays", "salary", "employmentType", "company"})
    private EmployeeDTO employee;
    @JsonIgnoreProperties({"name", "address", "phone"})
    private CompanyDTO company;
    private BigDecimal amount;
}
