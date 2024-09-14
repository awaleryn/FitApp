package com.waleryn.fitapp.calories.bmr;

import com.waleryn.fitapp.calories.ActivityLevel;
import com.waleryn.fitapp.calories.Gender;
import org.springframework.stereotype.Component;

@Component
public class BmrCalculator {


    /**
     * Calculates BMR based on Mifflin-St Jeor Equation
     *
     * @param weight in kilograms
     * @param height in centimeters
     * @param age in years
     * @param gender (male/female)
     * @return BMR
     */
    public double calculateBmr(double weight, double height, int age, Gender gender) {
        if (gender.name().equalsIgnoreCase(Gender.MALE.getType())) {
            return (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else if (gender.name().equalsIgnoreCase(Gender.FEMALE.getType())) {
            return (10 * weight) + (6.25 * height) - (5 * age) - 16;
        } else {
            throw new IllegalArgumentException("Invalid gender: " + gender);
        }
    }

    /**
     * Calculates your total daily calorie requirement (TDEE) based on your physical activity level.
     *
     * @param bmr Basic metabolic rate (BMR)
     * @param activityLevel Level of physical activity
     * @return TDEE
     */
    public double calculateTdee(double bmr, ActivityLevel activityLevel) {
        return switch (activityLevel) {
            case SEDENTARY -> bmr * 1.2;
            case LIGHTLY_ACTIVE -> bmr * 1.375;
            case MODERATELY_ACTIVE -> bmr * 1.55;
            case VERY_ACTIVE -> bmr * 1.725;
            case EXTRA_ACTIVE -> bmr * 1.9;
        };
    }
}
