package com.example.intro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bonus")
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bonus")
    @SequenceGenerator(
            name = "seq_bonus",
            sequenceName = "seq_bonus",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Column(
            name = "amount",
            precision = 10,
            scale = 2
    )
    private BigDecimal amount;

}
