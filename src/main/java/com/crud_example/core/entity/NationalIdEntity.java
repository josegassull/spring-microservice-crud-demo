package com.crud_example.core.entity;

import com.crud_example.core.types.IdentificationType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NationalIdEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID nationalIdId;
    private Long idNumber;
    private IdentificationType identificationType;
    private LocalDate issuanceDate;
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
