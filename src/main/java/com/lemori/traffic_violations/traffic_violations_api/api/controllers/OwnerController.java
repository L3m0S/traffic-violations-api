package com.lemori.traffic_violations.traffic_violations_api.api.controllers;

import com.lemori.traffic_violations.traffic_violations_api.api.dtos.PageDTO;
import com.lemori.traffic_violations.traffic_violations_api.api.mappers.OwnerMapper;
import com.lemori.traffic_violations.traffic_violations_api.domain.models.Owner;
import com.lemori.traffic_violations.traffic_violations_api.domain.repositories.OwnerRepository;
import com.lemori.traffic_violations.traffic_violations_api.domain.services.OwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private OwnerService ownerService;

    @GetMapping("")
    public PageDTO<Owner> findAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return OwnerMapper.INSTANCE.map(
                ownerService.findAll(pageable)
        );
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<Owner> getById(@PathVariable Long ownerId) {
        return ownerRepository.findById(ownerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Owner createOwner(@Valid @RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long ownerId,
                                             @Valid @RequestBody Owner owner) {

        if (!ownerRepository.existsById(ownerId)) {
            return ResponseEntity.notFound().build();
        }

        owner.setId(ownerId);
        return ResponseEntity.ok(ownerRepository.save(owner));
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long ownerId) {
        ownerService.deleteById(ownerId);
        return ResponseEntity.noContent().build();
    }


}
