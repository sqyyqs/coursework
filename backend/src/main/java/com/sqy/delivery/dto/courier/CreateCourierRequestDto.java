package com.sqy.delivery.dto.courier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqy.delivery.domain.ClientDetails;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "сущность для создания курьера")
public record CreateCourierRequestDto(
        @Schema(description = "сведения о курьере") ClientDetails courierDetails
) {
    @JsonCreator
    public CreateCourierRequestDto(@JsonProperty("courierDetails") ClientDetails courierDetails) {
        this.courierDetails = Objects.requireNonNull(courierDetails, "Courier details can't be null.");
    }
}
