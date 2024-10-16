package com.waleryn.fitapp.intake;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface DailyIntakeMapper {

    @Mappings({
            @Mapping(target = "caloriesToday", expression = "java(calculateDaily(dailyIntake.getTotalCalories(), dailyIntake.getUser().getDailyCaloricNeeds()))"),
            @Mapping(target = "proteinToday", expression = "java(calculateDaily(dailyIntake.getTotalProtein(), dailyIntake.getUser().getProteinNeeds()))"),
            @Mapping(target = "fatToday", expression = "java(calculateDaily(dailyIntake.getTotalFat(), dailyIntake.getUser().getFatNeeds()))"),
            @Mapping(target = "carbohydratesToday", expression = "java(calculateDaily(dailyIntake.getTotalCarbohydrates(), dailyIntake.getUser().getCarbNeeds()))")
    })
    DailyIntakeDto toDto(DailyIntake dailyIntake);

    List<DailyIntakeDto> toDto(List<DailyIntake> dailyIntakeList);


    default String calculateDaily(double consumed, double need) {
        return String.format("You have consumed %.2f / %.2f of your daily caloric needs.", consumed, need);
    }
}
