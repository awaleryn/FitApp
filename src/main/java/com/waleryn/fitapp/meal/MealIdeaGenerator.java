package com.waleryn.fitapp.meal;

import com.waleryn.fitapp.gpt.ChatGptServiceHelper;
import com.waleryn.fitapp.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class MealIdeaGenerator {

    private final ChatGptServiceHelper chatGptServiceHelper;

    protected abstract String getMealType();

    public String generateMealIdeas(List<String> productItems) {
        String question = String.format(
                Constants.MEAL_IDEAS_FORMAT,
                String.join(Constants.JOIN_DELIMITER, productItems), getMealType());
        return chatGptServiceHelper.getChatGptResponse(question);
    }

}
