package com.lemori.traffic_violations.traffic_violations_api.domain.models;

import com.lemori.traffic_violations.traffic_violations_api.domain.validations.VehicleValidationGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class Owner {

    @NotNull(groups = VehicleValidationGroup.OwnerId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Email
    @Column(nullable = false, unique = true)
    private String email;
}
