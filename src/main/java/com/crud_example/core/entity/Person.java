package com.crud_example.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import javax.persistence.Id;
import java.util.UUID;

@MappedSuperclass
public class Person implements Serializable{
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private int age;

    public Person(UUID id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
