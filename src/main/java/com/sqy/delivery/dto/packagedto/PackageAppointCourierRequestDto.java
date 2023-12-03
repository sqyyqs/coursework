package com.sqy.delivery.dto.packagedto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "сущность для назначения курьера")
public record PackageAppointCourierRequestDto(
        @Schema(description = "id посылки") long packageId,
        @Schema(description = "id курьера") long courierId
) {
}
