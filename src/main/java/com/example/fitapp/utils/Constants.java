package com.example.fitapp.utils;

public class Constants {

    private Constants() {}

    public static String BEARER = "Bearer ";
    public static int BEARER_TOKEN = 7; // substring token from 7th position from BEARER
    public static final String PRODUCT_RECOMMENDATION = "Recommend me healthy products that I can buy";
    public static final String MEAL_IDEAS_FORMAT = "I have %s in my fridge. Give me three ideas for a fit %s";
    public static final String JOIN_DELIMITER = ", ";
    public static final String GPT_MODEL = "gpt-3.5-turbo";
}
