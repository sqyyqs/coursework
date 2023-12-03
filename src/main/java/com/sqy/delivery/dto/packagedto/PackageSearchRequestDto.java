package com.sqy.delivery.dto.packagedto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqy.delivery.domain.packageEntity.PackageStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Schema(description = "сущность для поиска посылок")
public record PackageSearchRequestDto(
        @Schema(description = "допустимые id") Set<Long> ids,
        @Schema(description = "допустимые статусы") Set<PackageStatus> permissibleStatuses,
        @Schema(description = "допустимые id курьеров") Set<Long> courierIds,
        @Schema(description = "допустимые id юзеров") Set<Long> userIds
) {
    @JsonCreator
    public PackageSearchRequestDto(@JsonProperty("ids")  @Nullable Set<Long> ids,
                                   @JsonProperty("permissibleStatuses") @Nullable Set<PackageStatus> permissibleStatuses,
                                   @JsonProperty("courierIds") @Nullable Set<Long> courierIds,
                                   @JsonProperty("userIds") @Nullable Set<Long> userIds) {
        this.ids = Objects.requireNonNullElse(ids, Collections.emptySet());
        this.permissibleStatuses = Objects.requireNonNullElse(permissibleStatuses, Collections.emptySet());
        this.courierIds = Objects.requireNonNullElse(courierIds, Collections.emptySet());
        this.userIds = Objects.requireNonNullElse(userIds, Collections.emptySet());
    }

}
