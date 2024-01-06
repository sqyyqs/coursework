package com.sqy.delivery.service;

import com.sqy.delivery.dto.AdministratorDto;
import com.sqy.delivery.dto.TokenWrapper;
import com.sqy.delivery.dto.user.UserCreateRequestDto;
import com.sqy.delivery.dto.user.UserCredentialsDto;
import org.springframework.http.ResponseEntity;

public interface AdministratorService {

    ResponseEntity<TokenWrapper> login(UserCredentialsDto userCredentialsDto);

    ResponseEntity<AdministratorDto> create(UserCreateRequestDto userCreateRequestDto);
}
