package com.sqy.delivery.dto;

import com.sqy.delivery.domain.ClientDetails;
import io.swagger.v3.oas.annotations.media.Schema;

public record AdministratorDto(
        @Schema(description = "id") long id,
        @Schema(description = "сведения об админе") ClientDetails adminDetails) {

}
