package com.waleryn.fitapp.calories;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String type;

    Gender(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
