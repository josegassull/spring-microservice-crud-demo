package com.crud_example.app.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class CustomerRequestDto {
    private UUID customerId;
    private String name;
    private Integer age;
}
