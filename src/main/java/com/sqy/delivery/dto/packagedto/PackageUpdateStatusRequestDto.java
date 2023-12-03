package com.sqy.delivery.dto.packagedto;

import com.sqy.delivery.domain.packageEntity.PackageStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "сущность для обновления статуса посылки(есть определенные правила)")
public record PackageUpdateStatusRequestDto(
        @Schema(description = "id посылки") long packageId,
        @Schema(description = "новый статус") PackageStatus newStatus
) {
}
