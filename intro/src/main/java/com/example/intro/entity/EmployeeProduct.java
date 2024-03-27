package com.example.intro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee_product")
public class EmployeeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_product_seq")
    @SequenceGenerator(
            name = "employee_product_seq",
            sequenceName = "employee_product_seq",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "product_id")
    private Product product;
}