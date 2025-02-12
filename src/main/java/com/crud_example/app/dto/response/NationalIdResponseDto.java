package com.crud_example.app.dto.response;

import com.crud_example.core.types.IdentificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class NationalIdResponseDto {
    private UUID nationalIdId;
    private Long idNumber;
    private IdentificationType identificationType;
    private LocalDate issuanceDate;
    private LocalDate expirationDate;
    private UUID customerId;
}