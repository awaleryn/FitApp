package com.waleryn.fitapp.meal;

import com.waleryn.fitapp.gpt.ChatGptServiceHelper;
import org.springframework.stereotype.Component;

@Component
public class LunchIdeaGenerator extends MealIdeaGenerator {

    public LunchIdeaGenerator(ChatGptServiceHelper chatGptServiceHelper) {
        super(chatGptServiceHelper);
    }

    @Override
    protected String getMealType() {
        return MealType.LUNCH.getType();
    }
}
