package com.example.intro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private String barcode;
}
