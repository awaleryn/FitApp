package com.waleryn.fitapp.calories;

import com.waleryn.fitapp.user.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface CaloriesMapper {

    CaloriesDto toDto(User user);

    @InheritInverseConfiguration
    User toEntity(CaloriesDto caloriesDto);
}
