package com.crud_example.app.dto.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    private static final String DEFAULT_ERROR_DESCRIPTION = "An error occurred, please try again";
    private static final String BAD_REQUEST_ERROR_DESCRIPTION = "We have encountered an error, please review your request";
    private static final String FIELDS_BAD_REQUEST_ERROR_DESCRIPTION = "There are wrong or missing fields, please review your request";

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionDto> handleCustomException(CustomException ex) {

        CustomExceptionDto response = buildExceptionDto(ex);

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionDto> handleGenericException(Exception ex) {

        CustomExceptionDto response = buildExceptionDto(DEFAULT_ERROR_DESCRIPTION, ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomExceptionDto> handleBadRequestException(HttpMessageNotReadableException ex) {

        CustomExceptionDto exceptionDto = buildExceptionDto(BAD_REQUEST_ERROR_DESCRIPTION, ex.getMessage());

        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomExceptionDto> handleFieldsBadRequestException(ConstraintViolationException ex) {

        CustomExceptionDto exceptionDto = buildExceptionDto(FIELDS_BAD_REQUEST_ERROR_DESCRIPTION, ex.getConstraintViolations());

        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);

    }

    private CustomExceptionDto buildExceptionDto(String description, Set<ConstraintViolation<?>> constraintViolations) {
        return CustomExceptionDto
                .builder()
                .description(description)
                .fields(getErrorFields(constraintViolations))
                .build();
    }

    private List<String> getErrorFields(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream().map(constraintViolation ->
                "Field " + constraintViolation.getPropertyPath() + " has an improper value: "
                        + constraintViolation.getInvalidValue()).collect(Collectors.toList());

    }

    private CustomExceptionDto buildExceptionDto(String description, String message) {
        return CustomExceptionDto
                .builder()
                .description(description)
                .message(message)
                .build();
    }

    private CustomExceptionDto buildExceptionDto(CustomException ex) {
        return CustomExceptionDto
                .builder()
                .description(ex.getDescription())
                .message(ex.getMessage())
                .build();
    }
}