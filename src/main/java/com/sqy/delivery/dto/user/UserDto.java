package com.sqy.delivery.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqy.delivery.domain.ClientDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Objects;

@Builder
@Schema(description = "юзер")
public record UserDto(
        @Schema(description = "id") long id,
        @Schema(description = "сведения о юзере") ClientDetails userDetails,
        @Schema(description = "рабочий ли аккаунт") boolean isSuspended
) {
    public UserDto(@JsonProperty("id") long id,
                   @JsonProperty("userDetails") ClientDetails userDetails,
                   @JsonProperty("isSuspended") boolean isSuspended) {
        this.id = id;
        this.userDetails = Objects.requireNonNull(userDetails, "User details can't be null.");
        this.isSuspended = isSuspended;
    }
}
