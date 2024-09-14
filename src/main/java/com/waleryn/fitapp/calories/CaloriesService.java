package com.waleryn.fitapp.calories;

import com.waleryn.fitapp.calories.bmi.BmiCalculator;
import com.waleryn.fitapp.calories.bmr.BmrCalculator;
import com.waleryn.fitapp.user.User;
import com.waleryn.fitapp.user.UserRepository;
import com.waleryn.fitapp.utils.MathUtils;
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

    private void calculateMacros(double caloricNeeds, User user) {
        user.setCarbNeeds(MathUtils.roundToTwoDecimalPlaces((0.4 * caloricNeeds) / 4));
        user.setProteinNeeds(MathUtils.roundToTwoDecimalPlaces((0.35 * caloricNeeds) / 4));
        user.setFatNeeds(MathUtils.roundToTwoDecimalPlaces((0.25 * caloricNeeds) / 9));
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
    public CaloriesDto updateNutritionNeeds(
            double weight,
            double height,
            int age,
            Gender gender,
            ActivityLevel activityLevel,
            Goal goal,
            Principal connectedUser
    ) {
        double bmr = bmrCalculator.calculateBmr(weight, height, age, gender);
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        double tdee = MathUtils.roundToTwoDecimalPlaces(bmrCalculator.calculateTdee(bmr, activityLevel));

        switch (goal) {
            case LOSE_WEIGHT -> user.setDailyCaloricNeeds(tdee - 500);
            case GAIN_WEIGHT -> user.setDailyCaloricNeeds(tdee + 500);
            case MAINTAIN -> user.setDailyCaloricNeeds(tdee);
        }

        calculateMacros(user.getDailyCaloricNeeds(), user);

        double bmi = MathUtils.roundToTwoDecimalPlaces(bmiCalculator.calculateBmi(weight, height));
        user.setBmi(bmi);
        user.setCategory(bmiCalculator.getBmiCategory(bmi));

        userRepository.save(user);
        return caloriesMapper.toDto(user);
    }

}
