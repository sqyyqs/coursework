package com.sqy.delivery.dto.packagedto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqy.delivery.domain.Address;
import com.sqy.delivery.domain.PackageDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Objects;

@Builder
@Schema(description = "сущность для создания посылки")
public record CreatePackageRequestDto(

        @Schema(description = "сведения о посылке")
        PackageDetails packageDetails,

        @Schema(description = "откуда(адрес), сейчас игнорируется")
        Address fromAddress,

        @Schema(description = "от кого(id)")
        long fromUserId,

        @Schema(description = "кому(адрес), сейчас игнорируется")
        Address toAddress,

        @Schema(description = "кому(id)")
        long toUserId
) {

    @JsonCreator
    public CreatePackageRequestDto(
            @JsonProperty("packageDetails") PackageDetails packageDetails,
            @JsonProperty("fromAddress") Address fromAddress,
            @JsonProperty("fromUserId") long fromUserId,
            @JsonProperty("toAddress") Address toAddress,
            @JsonProperty("toUserId") long toUserId) {
        this.packageDetails = Objects.requireNonNull(packageDetails, "Package details can't be null,");
        this.fromAddress = Objects.requireNonNull(fromAddress, "From address can't be null.");
        this.fromUserId = fromUserId;
        this.toAddress = Objects.requireNonNull(toAddress, "To address can't be null.");
        this.toUserId = toUserId;
    }
}
