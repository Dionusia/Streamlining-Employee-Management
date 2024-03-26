package com.example.intro.entity;

import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

@Setter
@Getter
@ParameterObject
public class Request {
    private Double salary;
    private String season;
    private Long companyId;
}
