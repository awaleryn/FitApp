package com.example.fitapp.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductType {
    FRUIT("Fruit", "Fruits such as apples, bananas, etc."),
    VEGETABLE("Vegetable", "Vegetables such as carrots, broccoli, etc."),
    MEAT("Meat", "Meat products such as chicken, beef, etc."),
    DAIRY("Dairy", "Dairy products such as milk, cheese, etc."),
    GRAIN("Grain", "Grains and cereals such as rice, wheat, etc."),
    NUT("Nut", "Nuts such as almonds, peanuts, etc."),
    LEGUME("Legume", "Legumes such as beans, lentils, etc."),
    SEAFOOD("Seafood", "Seafood such as fish, shrimp, etc."),
    SNACK("Snack", "Snack foods such as chips, crackers, etc."),
    BEVERAGE("Beverage", "Beverages such as water, juice, etc."),
    SWEET("Sweet", "Sweet foods such as candy, chocolate, etc."),
    SPICE("Spice", "Spices such as salt, pepper, etc."),
    OIL("Oil", "Oils such as olive oil, sunflower oil, etc."),
    CONDIMENT("Condiment", "Condiments such as ketchup, mustard, etc.");

    private final String displayName;
    private final String description;
}
