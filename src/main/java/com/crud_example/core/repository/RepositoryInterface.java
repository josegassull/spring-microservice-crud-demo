package com.crud_example.core.repository;

import java.util.UUID;

public interface RepositoryInterface<T> {

    public void save(T t);


    public T findStudentById(UUID id);
}
