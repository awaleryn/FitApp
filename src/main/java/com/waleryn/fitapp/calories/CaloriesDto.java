package com.waleryn.fitapp.calories;

import com.waleryn.fitapp.calories.bmi.BmiCategory;
import lombok.Value;

@Value
public class CaloriesDto {

    double dailyCaloricNeeds;
    double proteinNeeds;
    double fatNeeds;
    double carbNeeds;
    double bmi;
    BmiCategory category;
}
