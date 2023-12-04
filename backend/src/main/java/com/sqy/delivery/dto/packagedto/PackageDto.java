package com.sqy.delivery.dto.packagedto;

import com.sqy.delivery.domain.Address;
import com.sqy.delivery.domain.PackageDetails;
import com.sqy.delivery.domain.packageEntity.PackageStatus;
import com.sqy.delivery.dto.courier.CourierDto;
import com.sqy.delivery.dto.user.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Schema(description = "посылка")
public record PackageDto(
        @Schema(description = "id") long id,
        @Schema(description = "статус") PackageStatus packageStatus,
        @Schema(description = "сведения о посылке") PackageDetails packageDetails,
        @Schema(description = "откуда(сейчас игнорируется)") Address fromAddress,
        @Schema(description = "от кого") UserDto from,
        @Schema(description = "куда(сейчас игнорируется)") Address toAddress,
        @Schema(description = "кому (сейчас игнорируется)") UserDto to,
        @Nullable @Schema(description = "курьер") CourierDto courier,
        @Schema(description = "ожидаемое время доставки(сейчас не работает)") LocalDateTime expectedDeliveryTime
) {

    public PackageDto(long id, PackageStatus packageStatus, PackageDetails packageDetails,
                      Address fromAddress, UserDto from,
                      Address toAddress, UserDto to,
                      @Nullable CourierDto courier, LocalDateTime expectedDeliveryTime) {
        this.id = id;
        this.packageStatus = Objects.requireNonNull(packageStatus, "Package status can't be null.");
        this.packageDetails = Objects.requireNonNull(packageDetails, "Package details can't be null,");
        this.fromAddress = Objects.requireNonNull(fromAddress, "From address can't be null.");
        this.from = Objects.requireNonNull(from, "From user can't be null.");
        this.toAddress = Objects.requireNonNull(toAddress, "To address can't be null.");
        this.to = Objects.requireNonNull(to, "To user can't be null.");
        this.courier = courier;
        this.expectedDeliveryTime = Objects.requireNonNull(expectedDeliveryTime, "Expected delivery time can't be null.");
    }
}
