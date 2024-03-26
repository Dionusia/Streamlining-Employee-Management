package com.example.intro.entity;

import lombok.Getter;

@Getter
public enum BonusRate {
    AUTUMN("autumn", 0.4),
    SPRING("spring", 0.6),
    SUMMER("summer", 0.7),
    WINTER("winter", 1.3);

    private final String season;
    private final double rate;

    BonusRate(String season, double rate) {
        this.season = season;
        this.rate = rate;
    }

    // Resolve bonus rate based on the provided season without modification
    public static BonusRate resolveBySeason(String season) {
        for (BonusRate bonusRate : values()) {
            if (bonusRate.getSeason().equalsIgnoreCase(season)) {
                return bonusRate;
            }
        }
        throw new IllegalArgumentException("No constant with season name " + season + " found");
    }
}