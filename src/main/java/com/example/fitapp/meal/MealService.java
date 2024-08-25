package com.example.fitapp.meal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final BreakfastIdeaGenerator breakfastIdeaGenerator;
    private final LunchIdeaGenerator lunchIdeaGenerator;
    private final DinnerIdeaGenerator dinnerIdeaGenerator;

    public String findBreakfastIdeasGPT(List<String> productItems) {
        return breakfastIdeaGenerator.generateMealIdeas(productItems);
    }

    public String findLunchIdeasGPT(List<String> productItems) {
        return lunchIdeaGenerator.generateMealIdeas(productItems);
    }

    public String findDinnerIdeasGPT(List<String> productItems) {
        return dinnerIdeaGenerator.generateMealIdeas(productItems);
    }
}
