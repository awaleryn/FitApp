package com.example.fitapp.meal;

import com.example.fitapp.gpt.ChatGptServiceHelper;
import org.springframework.stereotype.Component;

import static com.example.fitapp.meal.MealType.*;

@Component
public class BreakfastIdeaGenerator extends MealIdeaGenerator {

    public BreakfastIdeaGenerator(ChatGptServiceHelper chatGptServiceHelper) {
        super(chatGptServiceHelper);
    }

    @Override
    protected String getMealType() {
        return BREAKFAST.getType();
    }
}
