package com.example.fitapp.meal;

import com.example.fitapp.gpt.ChatGptServiceHelper;
import org.springframework.stereotype.Component;

import static com.example.fitapp.meal.MealType.*;

@Component
public class DinnerIdeaGenerator extends MealIdeaGenerator {

    public DinnerIdeaGenerator(ChatGptServiceHelper chatGptServiceHelper) {
        super(chatGptServiceHelper);
    }

    @Override
    protected String getMealType() {
        return DINNER.getType();
    }
}
