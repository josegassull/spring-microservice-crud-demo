package com.crud_example.app.dto.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
public class CustomerRequestDto {
    private UUID customerId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Age is required")
    @PositiveOrZero(message = "Age must be at least 0")
    private Integer age;
}
