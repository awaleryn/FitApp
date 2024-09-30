package com.waleryn.fitapp.user;

import lombok.Value;

@Value
public class UserDto {

    Integer id;
    String firstName;
    String lastName;
    String email;
}
