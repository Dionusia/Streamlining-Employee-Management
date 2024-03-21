package com.example.intro.entity;

import lombok.Getter;

@Getter
public enum VacationStatus {
    PENDING,
    APPROVED,
    REJECTED;

    public String toDbValue() {
        return this.name().toLowerCase();
    }

    public static VacationStatus from(String status) {

        return VacationStatus.valueOf(status.toUpperCase());
    }

}