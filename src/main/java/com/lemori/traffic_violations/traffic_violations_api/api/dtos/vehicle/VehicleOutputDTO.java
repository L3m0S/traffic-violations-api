package com.lemori.traffic_violations.traffic_violations_api.api.dtos.vehicle;

import com.lemori.traffic_violations.traffic_violations_api.domain.models.VehicleStatus;

import java.time.LocalDateTime;

public record VehicleOutputDTO(
        Long id,
        Long ownerId,
        String licencePlate,
        VehicleStatus status,
        String model,
        String brand,
        LocalDateTime createdAt,
        LocalDateTime seizedAt
) {
}
