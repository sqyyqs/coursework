package com.sqy.delivery.dto.courier;

import com.sqy.delivery.domain.courier.CourierStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "сущность для обновления статуса курьера.")
public record CourierUpdateStatusRequestDto(
        @Schema(description = "id курьера")
        long courierId,

        @Schema(description = "новый статус курьера(не работает с ON_MODERATING и SUSPENDED)")
        CourierStatus newStatus
) {
}
