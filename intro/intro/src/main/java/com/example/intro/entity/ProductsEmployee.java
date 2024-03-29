package com.example.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsEmployee {
    private Long id;
    private String name;
    private String surname;

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
