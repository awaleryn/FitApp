package com.example.fitapp.calories.bmi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BmiCalculatorTest {

    BmiCalculator bmiCalculator;

    @BeforeEach
    void setUp() {
        bmiCalculator = new BmiCalculator();
    }

    @Test
    public void shouldReturnUnderweightBasedOnBmi() {
        var bmiCategory = bmiCalculator.getBmiCategory(18.4);

        assertEquals(bmiCategory, BmiCategory.UNDERWEIGHT);
    }

    @Test
    public void shouldCalculateCorrectBmi() {
        int weight = 78;
        int height = 185;

        var bmi = bmiCalculator.calculateBmi(weight, height);

        assertEquals(bmi, 22.79);
    }
}