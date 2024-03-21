package com.example.intro.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<VacationStatus, String> {

    @Override
    public String convertToDatabaseColumn(VacationStatus status) {
        return status.toDbValue();
    }

    @Override
    public VacationStatus convertToEntityAttribute(String dbData) {
        return VacationStatus.from(dbData);
    }


}