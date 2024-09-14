package com.waleryn.fitapp.meal;

import com.waleryn.fitapp.gpt.ChatGptServiceHelper;
import org.springframework.stereotype.Component;

import static com.waleryn.fitapp.meal.MealType.*;

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
