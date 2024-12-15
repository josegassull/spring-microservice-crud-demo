package com.crud_example.core.repository;

import com.crud_example.core.entity.Person;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class PersonRepository implements RepositoryInterface<Person>{
    private Map<UUID, Person> repository;

    public PersonRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public void save(Person person) {
        repository.put(person.getId(), person);
    }

    @Override
    public Person findStudentById(UUID id) {
        return repository.get(id);
    }
}
