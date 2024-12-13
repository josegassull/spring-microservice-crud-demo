package com.crud_example.app.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import java.util.UUID;

@MappedSuperclass
public abstract class Person implements Serializable{
    @Id
    @GeneratedValue
    private UUID id;

}
