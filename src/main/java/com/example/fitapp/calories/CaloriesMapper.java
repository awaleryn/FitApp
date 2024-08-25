package com.example.fitapp.calories;

import com.example.fitapp.user.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface CaloriesMapper {

    CaloriesDto toDto(User user);

    @InheritInverseConfiguration
    User toEntity(CaloriesDto caloriesDto);
}
