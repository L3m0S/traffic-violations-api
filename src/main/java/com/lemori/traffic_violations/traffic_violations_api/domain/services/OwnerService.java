package com.lemori.traffic_violations.traffic_violations_api.domain.services;

import com.lemori.traffic_violations.traffic_violations_api.domain.exceptions.ApiException;
import com.lemori.traffic_violations.traffic_violations_api.domain.models.Owner;
import com.lemori.traffic_violations.traffic_violations_api.domain.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional
    public Owner save(Owner owner) {
        if (ownerRepository.findByEmail(owner.getEmail()).filter(o -> !o.equals(owner)).isPresent()) {
            throw new ApiException("Email already in use!", 400);
        }

        return ownerRepository.save(owner);
    }

    @Transactional
    public void deleteById(Long ownerId) {
        if (!ownerRepository.existsById(ownerId)) {

        }
        ownerRepository.deleteById(ownerId);
        return;
    }

    public Page<Owner> findAll(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }
}
