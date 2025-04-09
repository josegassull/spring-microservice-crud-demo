package com.crud_example.core.repository;

import java.util.Set;
import java.util.UUID;

import com.crud_example.core.entity.NationalIdEntity;
import com.crud_example.core.types.IdentificationType;
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

    boolean existsByIdNumberAndIdentificationTypeAndCountry(Long idNumber, IdentificationType identificationType, String country);

    boolean existsByIdentificationTypeAndCountryAndCustomer_CustomerId(IdentificationType identificationType, String country, UUID customerCustomerId);
    /*
    ¿Qué es un méthodo derivado de JPA?
    Spring Data JPA lo interpreta automáticamente basándose en su nombre.
    Spring Data JPA permite definir métodos de consulta utilizando la convención de nomenclatura, lo que significa que
    , si seguimos ciertas reglas en los nombres de los métodos, Spring automáticamente genera la consulta SQL necesaria
     sin que tengamos que escribirla.
    Por ejemplo, findAllByCustomerCustomerId(UUID customerId) se desglosa así:
    findAllBy → Indica que queremos recuperar múltiples registros.
    CustomerCustomerId → Spring lo interpreta como el campo customerId dentro del objeto customer en NationalIdEntity.
    Spring Data JPA toma este nombre y genera internamente una consulta equivalente a:
    SELECT * FROM national_id WHERE customer_id = ?;
    */
}