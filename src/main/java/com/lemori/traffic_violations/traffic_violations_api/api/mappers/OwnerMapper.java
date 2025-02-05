package com.lemori.traffic_violations.traffic_violations_api.api.mappers;

import com.lemori.traffic_violations.traffic_violations_api.api.dtos.PageDTO;
import com.lemori.traffic_violations.traffic_violations_api.domain.models.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface OwnerMapper {

    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    default PageDTO<Owner> map(Page<Owner> ownersPage) {
        return new PageDTO<>(
                ownersPage.getContent(),
                ownersPage.getNumber(),
                ownersPage.getSize(),
                ownersPage.getTotalElements(),
                ownersPage.getTotalPages()
        );
    }
}
