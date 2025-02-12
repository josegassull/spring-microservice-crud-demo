package com.crud_example.core.repository;

import java.util.Set;
import java.util.UUID;

import com.crud_example.core.entity.NationalIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalIdRepository extends JpaRepository<NationalIdEntity, UUID> {

    //Query JPA
    @Query("SELECT n FROM NationalIdEntity n WHERE n.customer.customerId = :customerId")
    Set<NationalIdEntity> getAllByCustomerId(@Param("customerId") UUID customerId);

    //Query nativa
    @Query(value = "SELECT * FROM national_id n WHERE n.customer_id = :customerId", nativeQuery = true)
    Set<NationalIdEntity> findAllByCustomerId(UUID customerId);

    //JPA Named Query
    Set<NationalIdEntity> findAllByCustomerCustomerId(UUID customerID);
}