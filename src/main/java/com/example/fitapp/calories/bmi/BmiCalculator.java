package com.example.fitapp.calories.bmi;

import org.springframework.stereotype.Component;

import static com.example.fitapp.calories.bmi.BmiCategory.*;

@Component
public class BmiCalculator {


    public double calculateBmi(double weight, double height) {
        double heightM = height / 100;
        return weight / (heightM * heightM);
    }


    public BmiCategory getBmiCategory(double bmi) {
        if (bmi < 18.5) {
            return UNDERWEIGHT;
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return NORMAL_WEIGHT;
        } else if (bmi >= 25 && bmi < 29.9) {
            return OVERWEIGHT;
        } else {
            return OBESITY;
        }
    }
}
