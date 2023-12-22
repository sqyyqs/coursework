package com.sqy.delivery.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqy.delivery.domain.ClientDetails;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "сущность для создания юзера/админа")
public record UserCreateRequestDto(
        @Schema(description = "сведения о юзере/админе") ClientDetails userDetails,
        @Schema(description = "логин/пароль") UserCredentialsDto credentials
) {
    @JsonCreator
    public UserCreateRequestDto(@JsonProperty("userDetails") ClientDetails userDetails,
                                @JsonProperty("credentials") UserCredentialsDto credentials) {
        this.userDetails = Objects.requireNonNull(userDetails, "Client details can't be null.");
        this.credentials = Objects.requireNonNull(credentials, "User credentials can't be null.");
    }
}
