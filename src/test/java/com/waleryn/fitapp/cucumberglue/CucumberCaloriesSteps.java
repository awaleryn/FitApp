package com.waleryn.fitapp.cucumberglue;

import com.waleryn.fitapp.TestDataInitializer;
import com.waleryn.fitapp.calories.ActivityLevel;
import com.waleryn.fitapp.calories.Gender;
import com.waleryn.fitapp.calories.Goal;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberCaloriesSteps {

    @Autowired
    private MockMvc mockMvc;
    private MvcResult result;
    private final String jwtToken = TestDataInitializer.testJwtToken;
    private final CucumberCommonStep cucumberCommonStep;

    public CucumberCaloriesSteps(CucumberCommonStep cucumberCommonStep) {
        this.cucumberCommonStep = cucumberCommonStep;
    }

    @ParameterType("male|female")
    public Gender gender(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }

    @ParameterType("sedentary|lightly_active|moderately_active|very_active|extra_active")
    public ActivityLevel activityLevel(String level) {
        return ActivityLevel.valueOf(level.toUpperCase());
    }

    @ParameterType("lose_weight|maintain|gain_weight")
    public Goal goal(String goal) {
        return Goal.valueOf(goal.toUpperCase());
    }

    @When("the user requests nutrition needs for weight {double} kg, height {double} cm, age {int} years, gender {gender}, activity level {activityLevel}, and goal {goal}")
    public void theUserRequestsNutritionNeeds(double weight, double height, int age, Gender gender, ActivityLevel activityLevel, Goal goal) throws Exception {
        result = mockMvc.perform(get("/api/calories/nutrition")
                        .param("weightInKg", String.valueOf(weight))
                        .param("heightInCm", String.valueOf(height))
                        .param("ageInYears", String.valueOf(age))
                        .param("gender", gender.name())
                        .param("activityLevel", activityLevel.name())
                        .param("goal", goal.name())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andReturn();
        cucumberCommonStep.setResult(result);
    }

    @Then("the nutrition needs should be calculated")
    public void theNutritionNeedsShouldBeCalculated() throws UnsupportedEncodingException {
        String jsonResponse = result.getResponse().getContentAsString();
        System.out.println("Response: " + jsonResponse);
    }

    @When("the user requests nutrition needs for weight {double} kg, height {double} cm, age {int} years, gender {gender}, activity level {activityLevel}, and goal {goal} without authorization")
    public void theUserRequestsNutritionNeedsWithoutAuthorization(double weight, double height, int age, Gender gender, ActivityLevel activityLevel, Goal goal) throws Exception {
        result = mockMvc.perform(get("/api/calories/nutrition")
                        .param("weightInKg", String.valueOf(weight))
                        .param("heightInCm", String.valueOf(height))
                        .param("ageInYears", String.valueOf(age))
                        .param("gender", gender.name())
                        .param("activityLevel", activityLevel.name())
                        .param("goal", goal.name()))
                .andExpect(status().isForbidden())
                .andReturn();
        cucumberCommonStep.setResult(result);
    }

}
