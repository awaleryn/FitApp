package com.waleryn.fitapp.calories.bmi;

import com.waleryn.fitapp.utils.MathUtils;
import org.springframework.stereotype.Component;

@Component
public class BmiCalculator {


    public double calculateBmi(double weight, double height) {
        double heightM = height / 100;
        return MathUtils.roundToTwoDecimalPlaces(weight / (heightM * heightM));
    }


    public BmiCategory getBmiCategory(double bmi) {
        if (bmi < 18.5) {
            return BmiCategory.UNDERWEIGHT;
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return BmiCategory.NORMAL_WEIGHT;
        } else if (bmi >= 25 && bmi < 29.9) {
            return BmiCategory.OVERWEIGHT;
        } else {
            return BmiCategory.OBESITY;
        }
    }
}
