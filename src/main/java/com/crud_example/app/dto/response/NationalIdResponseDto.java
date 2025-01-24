package com.crud_example.app.dto.response;

import com.crud_example.core.types.IdentificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NationalIdResponseDto {
    private Long idNumber;
    private IdentificationType identificationType;
    private LocalDate issuanceDate;
    private LocalDate expirationDate;
}