package com.lemori.traffic_violations.traffic_violations_api.domain.services;

import com.lemori.traffic_violations.traffic_violations_api.domain.exceptions.ApiException;
import com.lemori.traffic_violations.traffic_violations_api.domain.models.Owner;
import com.lemori.traffic_violations.traffic_violations_api.domain.models.Vehicle;
import com.lemori.traffic_violations.traffic_violations_api.domain.models.VehicleStatus;
import com.lemori.traffic_violations.traffic_violations_api.domain.repositories.OwnerRepository;
import com.lemori.traffic_violations.traffic_violations_api.domain.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public Vehicle save(Vehicle vehicle) {

        if(vehicle.getId() != null) {
           var vehicleExists = vehicleRepository.findById(vehicle.getId())
                    .orElseThrow(() -> new ApiException("Vehicle with given ID not found!", 404));
           vehicle.setCreatedAt(vehicleExists.getCreatedAt());
           vehicle.setStatus(vehicleExists.getStatus());
        }

        boolean licencePlateAlreadyExists = vehicleRepository.findByLicencePlate(vehicle.getLicencePlate())
                .filter(v -> !v.equals(vehicle))
                .isPresent();

        if (licencePlateAlreadyExists) {
            throw new ApiException("Licence Plate already registered!", 400);
        }

        Optional<Owner> ownerExists = ownerRepository.findById(vehicle.getOwner().getId());

        if (ownerExists.isEmpty()) {
            throw new ApiException("Owner with given ID not found!", 404);
        }

        if (vehicle.getId() == null) {
            vehicle.setStatus(VehicleStatus.REGULAR);
        }

        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public void deleteById(Long vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new ApiException("Vehicle whit given ID not found!", 404);
        }
        vehicleRepository.deleteById(vehicleId);
    }

    public Vehicle findById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ApiException("Vehicle with given ID not found!", 404));
    }

    public Page<Vehicle> getVehiclesPaginated(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }
}
