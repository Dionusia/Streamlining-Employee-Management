package com.example.intro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_company")
    @SequenceGenerator(
            name = "seq_company",
            sequenceName = "seq_company",
            allocationSize = 50
    )
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    @Size(max = 20)
    private String phone;

}
