package com.sqy.delivery.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqy.delivery.domain.ClientDetails;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "сущность для создания юзера")
public record UserCreateRequestDto(
        @Schema(description = "сведения о юзере") ClientDetails userDetails
) {
    @JsonCreator
    public UserCreateRequestDto(@JsonProperty("userDetails") ClientDetails userDetails) {
        this.userDetails = Objects.requireNonNull(userDetails, "Client details can't be null.");
    }
}
