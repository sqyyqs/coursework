package com.sqy.delivery.service.mapper;

import com.sqy.delivery.domain.user.User;
import com.sqy.delivery.dto.user.UserCreateRequestDto;
import com.sqy.delivery.dto.user.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userDetails(user.getUserDetails())
                .isSuspended(user.isSuspended())
                .build();
    }

    public static User fromCreateDto(UserCreateRequestDto userCreateRequestDto) {
        return User.builder()
                .userDetails(userCreateRequestDto.userDetails())
                .isSuspended(false)
                .build();
    }
}
