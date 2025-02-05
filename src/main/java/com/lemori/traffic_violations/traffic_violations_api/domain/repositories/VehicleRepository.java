package com.lemori.traffic_violations.traffic_violations_api.domain.repositories;

import com.lemori.traffic_violations.traffic_violations_api.domain.models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByLicencePlate(String plate);
    @NonNull Page<Vehicle> findAll(@NonNull Pageable pageable);
}
