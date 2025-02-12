package com.crud_example.app.dto.request;

import com.crud_example.core.types.IdentificationType;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class NationalIdRequestDto {
    private UUID nationalIdId;
    private Long idNumber;
    private IdentificationType identificationType;
    private LocalDate issuanceDate;
    private LocalDate expirationDate;
    private UUID customerId;
}
