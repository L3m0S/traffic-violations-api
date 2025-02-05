package com.lemori.traffic_violations.traffic_violations_api.api.dtos;

import java.util.List;

public record PageDTO<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
