package com.crud_example.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerResponseDto {
    private UUID customerId;
    private String name;
    private Integer age;
}
