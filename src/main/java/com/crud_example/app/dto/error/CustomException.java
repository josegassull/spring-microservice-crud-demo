package com.crud_example.app.dto.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Data
public class CustomException extends RuntimeException {

    private final String message;
    private final HttpStatus status;
    private final String description;
}