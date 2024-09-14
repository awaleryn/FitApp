package com.waleryn.fitapp.meal;

public enum MealType {
    BREAKFAST("breakfast"),
    LUNCH("lunch"),
    DINNER("dinner");

    private final String type;

    MealType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
