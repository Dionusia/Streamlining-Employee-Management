package com.example.intro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "product_seq",
            allocationSize = 50
    )
    private Long id;

    @NotNull
    @Column(name = "name")
    @Length(max = 255)
    private String name;

    @NotNull
    @Column(name = "description")
    @Length(max = 1000)
    private String description;

    @NotNull
    @Column(name = "barcode")
    @Length(max = 255)
    private String barcode;
}