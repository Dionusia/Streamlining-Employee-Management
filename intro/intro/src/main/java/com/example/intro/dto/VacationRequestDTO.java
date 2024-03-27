package com.example.intro.dto;

import com.example.intro.entity.Employee;
import com.example.intro.entity.VacationStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacationRequestDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private VacationStatus status;
    private Integer days;
    @JsonIgnoreProperties({"name", "surname", "email", "startDate", "vacationDays", "salary", "employmentType", "company"})
    private EmployeeDTO employee;
}
