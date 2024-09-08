package com.example.fitapp.cucumberglue;

import com.example.fitapp.TestDataInitializer;
import com.example.fitapp.calories.ActivityLevel;
import com.example.fitapp.calories.CaloriesService;
import com.example.fitapp.calories.Gender;
import com.example.fitapp.calories.Goal;
import com.example.fitapp.exception.UserDoesNotExistException;
import com.example.fitapp.user.User;
import com.example.fitapp.user.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.Principal;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberIntakeSteps {

    @Autowired
    private MockMvc mockMvc;
    private MvcResult result;
    private final String jwtToken = TestDataInitializer.testJwtToken;
    private final CucumberCommonStep cucumberCommonStep;
    private final UserRepository userRepository;
    private final CaloriesService caloriesService;
    private Principal principal;

    public CucumberIntakeSteps(
            CucumberCommonStep cucumberCommonStep, UserRepository userRepository,
            CaloriesService caloriesService
    ) {
        this.cucumberCommonStep = cucumberCommonStep;
        this.userRepository = userRepository;
        this.caloriesService = caloriesService;
    }


    @When("the user has assigned daily needs")
    public void theUserShouldHaveAssignedDailyNeeds() {
        Optional<User> optionalUser = userRepository.findByEmail("testuser@gmail.com");
        var user = optionalUser.orElseThrow(
                () -> new UserDoesNotExistException("User with this login doesn't exist!"));

        principal = new UsernamePasswordAuthenticationToken(user, null);
        caloriesService.updateNutritionNeeds(70.5, 175.0, 30, Gender.MALE, ActivityLevel.MODERATELY_ACTIVE, Goal.MAINTAIN, principal);
    }

    @And("adds new product to daily intake with {string} product name")
    public void theUserAddsProductToDailyIntake(String productName) throws Exception {

        result = mockMvc.perform(post("/api/intake/add")
                        .param("productName", productName)
                        .principal(principal)
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        cucumberCommonStep.setResult(result);
    }
}
