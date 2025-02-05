package com.lemori.traffic_violations.traffic_violations_api.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lemori.traffic_violations.traffic_violations_api.domain.validations.VehicleValidationGroup;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Vehicle {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = VehicleValidationGroup.OwnerId.class)
    @ManyToOne
    @NotNull
    private Owner owner;

    @Column
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String licencePlate;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private VehicleStatus status;

    @Column
    @NotBlank
    private String model;

    @Column
    @NotBlank
    private String brand;

    @Column(insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @Column()
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime seizedAt;

}
