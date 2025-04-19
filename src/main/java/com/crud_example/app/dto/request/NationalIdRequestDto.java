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

    @NotNull(message = "ID number must not be null")
    @Positive(message = "ID number must be positive")
    private Long idNumber;

    @NotNull(message = "ID type must not be null")
    private IdentificationType identificationType;

    private LocalDate issuanceDate;
    private LocalDate expirationDate;

    @NotNull(message = "Customer's ID must not be null")
    private UUID customerId;

    @NotBlank(message = "Country must not be blank")
    @Size.List({
            @Size(min = 3, message = "Country must have exactly three characters"),
            @Size(max = 3, message = "Country must have exactly three characters")
    })
    private String country;
}

/*
https://www.baeldung.com/java-validation
@NotNull
@NotEmpty
@Size
@Min
@Max
@Positive and @PositiveOrZero @NegativeOrZero
@Negative
 */
