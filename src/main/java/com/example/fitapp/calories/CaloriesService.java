package com.example.fitapp.calories;

import com.example.fitapp.calories.bmi.BmiCalculator;
import com.example.fitapp.calories.bmr.BmrCalculator;
import com.example.fitapp.user.User;
import com.example.fitapp.user.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CaloriesService {

    private final CaloriesMapper caloriesMapper;
    private final BmrCalculator bmrCalculator;
    private final BmiCalculator bmiCalculator;
    private final UserRepository userRepository;


    public CaloriesService(
            CaloriesMapper caloriesMapper,
            BmrCalculator bmrCalculator,
            BmiCalculator bmiCalculator,
            UserRepository userRepository) {
        this.caloriesMapper = caloriesMapper;
        this.bmrCalculator = bmrCalculator;
        this.bmiCalculator = bmiCalculator;
        this.userRepository = userRepository;
    }

    protected void calculateMacros(double caloricNeeds, User user) {
        user.setCarbNeeds(roundToTwoDecimalPlaces((0.4 * caloricNeeds) / 4));
        user.setProteinNeeds(roundToTwoDecimalPlaces((0.35 * caloricNeeds) / 4));
        user.setFatNeeds(roundToTwoDecimalPlaces((0.25 * caloricNeeds) / 9));
    }


    /**
     * Sets user's dailyCaloricNeeds, proteinNeeds, fatNeeds, carbNeeds, bmi and bmi category
     *
     * @param weight in kilograms
     * @param height in centimeters
     * @param age in years
     * @param gender (MALE/FEMALE)
     * @param  activityLevel (SEDENTARY/LIGHTLY_ACTIVE/MODERATELY_ACTIVE/VERY_ACTIVE/EXTRA_ACTIVE)
     * @param goal (LOSE_WEIGHT/MAINTAIN/GAIN_WEIGHT)
     * @return CaloriesDto
     */
    protected CaloriesDto updateNutritionNeeds(
            double weight,
            double height,
            int age,
            String gender,
            ActivityLevel activityLevel,
            Goal goal,
            Principal connectedUser
    ) {
        double bmr = bmrCalculator.calculateBmr(weight, height, age, gender);
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        double tdee = roundToTwoDecimalPlaces(bmrCalculator.calculateTdee(bmr, activityLevel));

        switch (goal) {
            case LOSE_WEIGHT -> user.setDailyCaloricNeeds(tdee - 500);
            case GAIN_WEIGHT -> user.setDailyCaloricNeeds(tdee + 500);
            case MAINTAIN -> user.setDailyCaloricNeeds(tdee);
        }

        calculateMacros(user.getDailyCaloricNeeds(), user);

        double bmi = roundToTwoDecimalPlaces(bmiCalculator.calculateBmi(weight, height));
        user.setBmi(bmi);
        user.setCategory(bmiCalculator.getBmiCategory(bmi));

        userRepository.save(user);
        return caloriesMapper.toDto(user);
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
