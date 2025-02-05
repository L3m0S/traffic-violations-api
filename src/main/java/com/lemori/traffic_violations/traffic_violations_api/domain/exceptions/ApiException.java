package com.lemori.traffic_violations.traffic_violations_api.domain.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    public Integer statusCode;
    public ApiException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
