package com.lemori.traffic_violations.traffic_violations_api.api.handlers;

import com.lemori.traffic_violations.traffic_violations_api.domain.exceptions.ApiException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("One or more fields are invalid!");

        Map<String, String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(
                        Collectors.toMap(objectError -> ((FieldError)objectError).getField(),
                                DefaultMessageSourceResolvable::getDefaultMessage)
                );

        problemDetail.setProperty("fields", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(ApiException.class)
    public ProblemDetail handleApiException(ApiException apiException) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(apiException.getStatusCode());
        problemDetail.setTitle(apiException.getMessage());

        return  problemDetail;
    }
}
