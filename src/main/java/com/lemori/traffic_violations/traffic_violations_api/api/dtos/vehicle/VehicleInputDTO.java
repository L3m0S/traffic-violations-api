package com.lemori.traffic_violations.traffic_violations_api.api.dtos.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record VehicleInputDTO(
        @NotNull Long ownerId,
        @NotBlank @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") String licencePlate,
        @NotBlank  String model,
        @NotBlank String brand
) {
}
