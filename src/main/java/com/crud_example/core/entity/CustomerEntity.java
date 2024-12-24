package com.crud_example.core.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID customerId;
    private String name;
    private Integer age;
}