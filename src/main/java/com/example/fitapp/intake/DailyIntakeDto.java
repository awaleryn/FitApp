package com.example.fitapp.intake;

import lombok.Value;

import java.time.LocalDate;

@Value
public class DailyIntakeDto {

    LocalDate date;
    String caloriesToday;
    String proteinToday;
    String fatToday;
    String carbohydratesToday;
}
