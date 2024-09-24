package com.waleryn.fitapp.meal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/breakfast")
    ResponseEntity<String> getBreakfastIdeasFromGPT(@RequestParam List<String> productItems) {
        return new ResponseEntity<>(
                mealService.findBreakfastIdeasGPT(productItems),
                HttpStatus.OK
        );
    }

    @GetMapping("/lunch")
    ResponseEntity<String> getLunchIdeasFromGPT(@RequestParam List<String> productItems) {
        return new ResponseEntity<>(
                mealService.findLunchIdeasGPT(productItems),
                HttpStatus.OK
        );
    }

    @GetMapping("/dinner")
    ResponseEntity<String> getDinnerIdeasFromGPT(@RequestParam List<String> productItems) {
        return new ResponseEntity<>(
                mealService.findDinnerIdeasGPT(productItems),
                HttpStatus.OK
        );
    }
}
