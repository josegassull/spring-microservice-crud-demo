package com.crud_example.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Builder
@Getter
@Setter
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private int age;
}
