package com.crud_example.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private UUID customerId;
    private String name;
    private int age;
}