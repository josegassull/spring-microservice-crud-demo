package com.crud_example.app.dto.request;

import com.crud_example.core.types.IdentificationType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class NationalIdRequestDto {
    private UUID nationalIdId;

    @NotNull
    @Positive
    private Long idNumber;

    @NotNull
    private IdentificationType identificationType;

    private LocalDate issuanceDate;
    private LocalDate expirationDate;

    @NotNull
    private UUID customerId;

    @NotBlank
    @Size.List({
            @Size(min = 3),
            @Size(max = 3)
    })
    private String country;
}

/*
@NotNull
@NotEmpty
@Size
@Min
@Max
@Positive and @PositiveOrZero @NegativeOrZero
@Negative
 */
