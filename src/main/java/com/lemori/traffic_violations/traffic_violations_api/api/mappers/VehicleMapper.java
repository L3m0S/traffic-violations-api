package com.lemori.traffic_violations.traffic_violations_api.api.mappers;

import com.lemori.traffic_violations.traffic_violations_api.api.dtos.PageDTO;
import com.lemori.traffic_violations.traffic_violations_api.api.dtos.vehicle.VehicleInputDTO;
import com.lemori.traffic_violations.traffic_violations_api.api.dtos.vehicle.VehicleOutputDTO;
import com.lemori.traffic_violations.traffic_violations_api.api.dtos.vehicle.VehicleUpdateInputDTO;
import com.lemori.traffic_violations.traffic_violations_api.domain.models.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper( VehicleMapper.class);

    @Mapping(source = "owner.id", target = "ownerId")
    VehicleOutputDTO vehicleToVehicleOutputDTO(Vehicle vehicle);

    @Mapping(source = "ownerId", target = "owner.id")
    Vehicle vehicleInputToVehicle(VehicleInputDTO vehicle);

    @Mapping(source = "ownerId", target = "owner.id")
    Vehicle vehicleUpdateInputToVehicle(VehicleUpdateInputDTO vehicle);

    default PageDTO<VehicleOutputDTO> map(Page<Vehicle> vehiclesPage) {
        List<VehicleOutputDTO> content = vehiclesPage.getContent()
                .stream()
                .map(this::vehicleToVehicleOutputDTO)
                .collect(Collectors.toList());

        return new PageDTO<>(
                content,
                vehiclesPage.getNumber(),
                vehiclesPage.getSize(),
                vehiclesPage.getTotalElements(),
                vehiclesPage.getTotalPages()
        );
    }
}
