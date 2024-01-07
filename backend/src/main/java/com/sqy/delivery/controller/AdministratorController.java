package com.sqy.delivery.controller;

import com.sqy.delivery.dto.AdministratorDto;
import com.sqy.delivery.dto.TokenWrapper;
import com.sqy.delivery.dto.user.UserCreateRequestDto;
import com.sqy.delivery.dto.user.UserCredentialsDto;
import com.sqy.delivery.service.AdministratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administrator")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Администратор api.")
public class AdministratorController {

    private final AdministratorService administratorService;

    @PostMapping("/create")
    @CrossOrigin("*")
    @Operation(summary = "Создание нового админа(не для продакшена).")
    public ResponseEntity<AdministratorDto> create(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        log.info("Invoke create({}).", userCreateRequestDto);
        return administratorService.create(userCreateRequestDto);
    }

    @PostMapping("/login")
    @CrossOrigin("*")
    @Operation(summary = "Вход по логину и паролю.")
    public ResponseEntity<TokenWrapper> login(@RequestBody UserCredentialsDto userCredentialsDto) {
        log.info("Invoke login({}).", userCredentialsDto);
        return administratorService.login(userCredentialsDto);
    }
}
