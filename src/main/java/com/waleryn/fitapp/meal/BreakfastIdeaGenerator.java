package com.waleryn.fitapp.meal;

import com.waleryn.fitapp.gpt.ChatGptServiceHelper;
import org.springframework.stereotype.Component;

@Component
public class BreakfastIdeaGenerator extends MealIdeaGenerator {

    public BreakfastIdeaGenerator(ChatGptServiceHelper chatGptServiceHelper) {
        super(chatGptServiceHelper);
    }

    @Override
    protected String getMealType() {
        return MealType.BREAKFAST.getType();
    }
}
