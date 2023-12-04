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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
@Schema(description = "сведения о человеке")
public class ClientDetails {

    @Nullable
    @Schema(description = "имя")
    String firstName;

    @Nullable
    @Schema(description = "фамилия")
    String lastName;

    @Nullable
    @Schema(description = "телефон(обязательно в формате 89991112223(11 цифр, без плюсов и (, -, )")
    String phoneNumber;
}
