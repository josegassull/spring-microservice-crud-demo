package com.crud_example.core.repository;

import java.util.UUID;
import com.crud_example.core.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, UUID> {

}
