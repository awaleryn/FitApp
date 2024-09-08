package com.example.fitapp.calories;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/calories")
@RequiredArgsConstructor
public class CaloriesController {

    private final CaloriesService caloriesService;

    @GetMapping("/nutrition")
    ResponseEntity<CaloriesDto> getNutritionNeeds(
            @RequestParam double weightInKg,
            @RequestParam double heightInCm,
            @RequestParam int ageInYears,
            @RequestParam Gender gender,
            @RequestParam ActivityLevel activityLevel,
            @RequestParam Goal goal,
            Principal connectedUser) {
        return new ResponseEntity<>(
                caloriesService.updateNutritionNeeds(
                        weightInKg,
                        heightInCm,
                        ageInYears,
                        gender,
                        activityLevel,
                        goal,
                        connectedUser),
                HttpStatus.OK
        );

    }
}
