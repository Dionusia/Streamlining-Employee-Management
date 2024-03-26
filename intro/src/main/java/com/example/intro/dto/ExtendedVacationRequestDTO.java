package com.example.intro.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ExtendedVacationRequestDTO extends VacationRequestDTO {
    private Integer holiday;

}