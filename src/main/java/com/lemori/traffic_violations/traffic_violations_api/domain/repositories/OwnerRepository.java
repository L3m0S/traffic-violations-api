package com.lemori.traffic_violations.traffic_violations_api.domain.repositories;

import com.lemori.traffic_violations.traffic_violations_api.domain.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findByNameContaining(String name);
    Optional<Owner> findByEmail(String email);
}
