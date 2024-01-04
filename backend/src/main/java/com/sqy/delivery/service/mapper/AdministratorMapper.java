package com.sqy.delivery.service.mapper;

import com.sqy.delivery.domain.user.UserCredentials;
import com.sqy.delivery.domain.user.administratior.Administrator;
import com.sqy.delivery.dto.AdministratorDto;
import com.sqy.delivery.dto.user.UserCreateRequestDto;
import com.sqy.delivery.dto.user.UserCredentialsDto;

public class AdministratorMapper {
    public static Administrator fromCreateDto(UserCreateRequestDto userCreateRequestDto) {
        return Administrator.builder()
                .credentials(mapCreds(userCreateRequestDto.credentials()))
                .userDetails(userCreateRequestDto.userDetails())
                .build();
    }

    public static AdministratorDto toDto(Administrator administrator) {
        return new AdministratorDto(administrator.getId(), administrator.getUserDetails());
    }

    private static UserCredentials mapCreds(UserCredentialsDto userCredentialsDto) {
        return new UserCredentials(userCredentialsDto.login(), userCredentialsDto.password());
    }
}
