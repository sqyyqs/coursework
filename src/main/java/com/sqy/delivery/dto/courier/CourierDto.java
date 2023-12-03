package com.sqy.delivery.dto.courier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqy.delivery.domain.ClientDetails;
import com.sqy.delivery.domain.courier.CourierStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Objects;

@Builder
@Schema(description = "курьер")
public record CourierDto(
        @Schema(description = "id") long id,
        @Schema(description = "статус курьера")  CourierStatus courierStatus,
        @Schema(description = "информация о курьере")  ClientDetails courierDetails
) {
    @JsonCreator
    public CourierDto(@JsonProperty("id") long id,
                      @JsonProperty("courierStatus") CourierStatus courierStatus,
                      @JsonProperty("courierDetails") ClientDetails courierDetails) {
        this.id = id;
        this.courierStatus = Objects.requireNonNull(courierStatus, "Courier status can't be null.");
        this.courierDetails = Objects.requireNonNull(courierDetails, "Courier details can't be null.");
    }
}
