package com.sqy.delivery.dto.courier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqy.delivery.domain.packageEntity.PackageStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Builder;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Builder
@Schema(description = "сущность для поиска по курьерам")
public record CourierSearchRequestDto(
        @Schema(description = "допустимые id")
        Set<Long> ids,

        @Schema(description = "допустимые статусы")
        Set<PackageStatus> permissibleStatuses,

        @Schema(description = "значения для поиск в имени и фамилии, содержится ли value, игнорируя регистр внутри имени или фамилии")
        @Nullable
        String value
) {

    public CourierSearchRequestDto(@JsonProperty("ids") Set<Long> ids,
                                   @JsonProperty("permissibleStatuses") Set<PackageStatus> permissibleStatuses,
                                   @JsonProperty("value") @Nullable String value) {
        this.ids = Objects.requireNonNullElse(ids, Collections.emptySet());
        this.permissibleStatuses = Objects.requireNonNullElse(permissibleStatuses, Collections.emptySet());
        this.value = value;
    }
}
