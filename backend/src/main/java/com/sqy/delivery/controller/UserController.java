package com.sqy.delivery.controller;

import com.sqy.delivery.dto.user.UserCreateRequestDto;
import com.sqy.delivery.dto.user.UserDto;
import com.sqy.delivery.dto.user.UserSearchRequestDto;
import com.sqy.delivery.service.UserService;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Пользователи api.")
public class UserController {

    private final UserService userService;

    @PostMapping("/search")
    @Operation(summary = "Поиск с пагинацией")
    public ResponseEntity<PagingResponse<UserDto>> search(@RequestBody PagingRequest<UserSearchRequestDto> userSearchRequestDto) {
        log.info("Invoke search({}).", userSearchRequestDto);
        return ResponseEntity.ok(userService.search(userSearchRequestDto));
    }

    @DeleteMapping("/suspend/{id}")
    @Operation(summary = "Удаление курьера из базы(ему проставляется isSuspended=true).")
    public ResponseEntity<UserDto> suspend(@PathVariable("id") long id) {
        log.info("Invoke suspend({}).", id);
        return userService.suspendById(id);
    }

    @PostMapping("/create")
    @Operation(summary = "Создание нового юзера.")
    public ResponseEntity<UserDto> create(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        log.info("Invoke create({}).", userCreateRequestDto);
        return userService.create(userCreateRequestDto);
    }

}
