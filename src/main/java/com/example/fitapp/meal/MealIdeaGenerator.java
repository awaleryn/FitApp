package com.example.fitapp.meal;

import com.example.fitapp.gpt.ChatGptServiceHelper;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.fitapp.utils.Constants.JOIN_DELIMITER;
import static com.example.fitapp.utils.Constants.MEAL_IDEAS_FORMAT;

@RequiredArgsConstructor
public abstract class MealIdeaGenerator {

    private final ChatGptServiceHelper chatGptServiceHelper;

    protected abstract String getMealType();

    public String generateMealIdeas(List<String> productItems) {
        String question = String.format(
                MEAL_IDEAS_FORMAT,
                String.join(JOIN_DELIMITER, productItems), getMealType());
        return chatGptServiceHelper.getChatGptResponse(question);
    }

}
