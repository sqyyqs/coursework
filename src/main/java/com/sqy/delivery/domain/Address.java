package com.sqy.delivery.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "адрес, сейчас игнорируется")
public class Address {

    @Schema(description = "улица")
    String street;
    @Schema(description = "номер дома")
    String houseNumber;
    @Schema(description = "индекс")
    String zipCode;
    @Schema(description = "город")
    String city;

    @Nullable
    @Schema(description = "подъезд/парадная")
    String entrance;

    @Nullable
    @Schema(description = "номер квартиры/офиса")
    String apartmentNumber;


}