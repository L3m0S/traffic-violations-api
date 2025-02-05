package com.lemori.traffic_violations.traffic_violations_api.api.controllers;

import com.lemori.traffic_violations.traffic_violations_api.api.dtos.PageDTO;
import com.lemori.traffic_violations.traffic_violations_api.api.dtos.vehicle.VehicleInputDTO;
import com.lemori.traffic_violations.traffic_violations_api.api.dtos.vehicle.VehicleOutputDTO;
import com.lemori.traffic_violations.traffic_violations_api.api.dtos.vehicle.VehicleUpdateInputDTO;
import com.lemori.traffic_violations.traffic_violations_api.api.mappers.VehicleMapper;
import com.lemori.traffic_violations.traffic_violations_api.domain.models.Vehicle;
import com.lemori.traffic_violations.traffic_violations_api.domain.services.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("")
    public PageDTO<VehicleOutputDTO> findAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return VehicleMapper.INSTANCE.map(
                vehicleService.findAll(pageable)
        );
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleOutputDTO> getVehicleById(@PathVariable Long vehicleId) {
        return ResponseEntity.ok().body(VehicleMapper.INSTANCE.vehicleToVehicleOutputDTO(vehicleService.findById(vehicleId)));
    }

    @PostMapping("")
    public ResponseEntity<VehicleOutputDTO> createVehicle(@Valid @RequestBody VehicleInputDTO vehicle) {

        return ResponseEntity.created(URI.create("")).body(
                VehicleMapper.INSTANCE.vehicleToVehicleOutputDTO(
                        vehicleService.save(VehicleMapper.INSTANCE.vehicleInputToVehicle(vehicle))
                )
        );
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleOutputDTO> updateVehicle(@PathVariable Long vehicleId,
                                                         @Valid @RequestBody VehicleUpdateInputDTO vehicleUpdateInputDto) {

        Vehicle vehicle = VehicleMapper.INSTANCE.vehicleUpdateInputToVehicle(vehicleUpdateInputDto);
        vehicle.setId(vehicleId);
        return ResponseEntity.ok(VehicleMapper.INSTANCE.vehicleToVehicleOutputDTO(vehicleService.save(vehicle)));
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteById(vehicleId);
        return ResponseEntity.noContent().build();
    }

}
