package com.example.intro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee")
    @SequenceGenerator(
            name = "seq_employee",
            sequenceName = "seq_employee",
            allocationSize = 50
    )
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Column(name = "vacation_days")
    private Integer vacationDays;

    @Column(
            name = "salary",
            precision = 10,
            scale = 2
    )
    private BigDecimal salary;

    @NotNull
    @Column(name = "employment_type")
    @Size(max = 20)
    private String employmentType;


    @ManyToOne
    @NotNull
    @JoinColumn(name = "company_id")
    private Company company;

}
