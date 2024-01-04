package com.sqy.delivery.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "сущность для хранения логина и пароля")
public record UserCredentialsDto(
        String login,
        String password
) {

    @JsonCreator
    public UserCredentialsDto(@JsonProperty("login") String login, @JsonProperty("password") String password) {
        this.login = Objects.requireNonNull(login, "Login can't be null.");
        this.password = Objects.requireNonNull(password, "Password can't be null.");
    }
}
