package com.crud_example.core.repository;

import com.crud_example.core.entity.NationalIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalIdRepository extends JpaRepository<NationalIdEntity, Long> {

}