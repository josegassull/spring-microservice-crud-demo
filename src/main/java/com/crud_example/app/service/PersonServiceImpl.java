package com.crud_example.app.service;

import com.crud_example.core.repository.PersonRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service("personService")
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

}
