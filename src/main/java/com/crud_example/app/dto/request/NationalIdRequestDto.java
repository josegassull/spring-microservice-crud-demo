package com.crud_example.app.dto.request;

import com.crud_example.core.types.IdentificationType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NationalIdRequestDto {
    private Long idNumber;
    private IdentificationType identificationType;
    private LocalDate issuanceDate;
    private LocalDate expirationDate;
}
