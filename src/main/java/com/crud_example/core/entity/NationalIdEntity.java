package com.crud_example.core.entity;

import com.crud_example.core.types.IdentificationType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class NationalIdEntity implements Serializable {

    @Id
    private Long idNumber;
    private IdentificationType identificationType;
    private LocalDate issuanceDate;
    private LocalDate expirationDate;
}
