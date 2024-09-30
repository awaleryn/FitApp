package com.waleryn.fitapp.user;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto toDto(User user);

}
