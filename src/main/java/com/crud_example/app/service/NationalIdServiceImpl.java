package com.crud_example.app.service;

import com.crud_example.app.dto.request.NationalIdRequestDto;
import com.crud_example.app.dto.response.NationalIdResponseDto;
import com.crud_example.core.entity.NationalIdEntity;
import com.crud_example.core.repository.CustomerRepository;
import com.crud_example.core.repository.NationalIdRepository;
import com.crud_example.core.utils.CustomerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service("nationalIdService")
@RequiredArgsConstructor
public class NationalIdServiceImpl implements NationalIdService {

    private final NationalIdRepository nationalIdRepository;
    private final CustomerRepository customerRepository;

    @Override
    public NationalIdResponseDto createNationalId(NationalIdRequestDto nationalIdRequestDto) {

        boolean existsCustomer = customerRepository.existsById(nationalIdRequestDto.getCustomerId());
        if(!existsCustomer){
            throw new IllegalArgumentException("ERROR: Invalid customer ID, please try again.");
        }

        //TODO 2: Si no valida, y lanza la excepción: ¿También va a cortar esta línea de flujo?
        CustomerUtils.validateIdentificationType(nationalIdRequestDto.getIdentificationType());

        //TODO 1: Duda, hay una diferencia entre NationalIdEntity y sus DTOs, y es que NationalIdEntity tiene un
        //atributo del tipo CusotmerEntity, y los DTOs, en cambio, tienen un UUID customerId ¿Qué hacer?
        NationalIdEntity nationalIdEntity = NationalIdEntity.builder()
                .nationalIdId(nationalIdRequestDto.getNationalIdId())
                .idNumber(nationalIdRequestDto.getIdNumber())
                .issuanceDate(nationalIdRequestDto.getIssuanceDate())
                .expirationDate(nationalIdRequestDto.getExpirationDate())
                .identificationType(nationalIdRequestDto.getIdentificationType())
                .build();

        NationalIdEntity savedNationalIdEntity = nationalIdRepository.save(nationalIdEntity);

        //TODO 1: Por ejemplo, aca, al buildear la respuesta, devolvemos el uuid, pero no una entidad de Customer
        return NationalIdResponseDto
                .builder()
                .nationalIdId(savedNationalIdEntity.getNationalIdId())
                .idNumber(savedNationalIdEntity.getIdNumber())
                .issuanceDate(savedNationalIdEntity.getIssuanceDate())
                .expirationDate(savedNationalIdEntity.getExpirationDate())
                .identificationType(savedNationalIdEntity.getIdentificationType())
                .customerId(nationalIdRequestDto.getCustomerId())
                .build();
    }

    @Override
    public NationalIdResponseDto getNationalId(UUID nationalIdId) {
        NationalIdEntity nationalIdEntity = nationalIdRepository.findById(nationalIdId)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Identification not found with ID: %s", nationalIdId))
                );

        return NationalIdResponseDto
                .builder()
                .nationalIdId(nationalIdEntity.getNationalIdId())
                .idNumber(nationalIdEntity.getIdNumber())
                .issuanceDate(nationalIdEntity.getIssuanceDate())
                .expirationDate(nationalIdEntity.getExpirationDate())
                .identificationType(nationalIdEntity.getIdentificationType())
                .customerId(nationalIdEntity.getCustomer().getCustomerId())
                .build();
    }

    @Override
    public NationalIdResponseDto updateNationalId(UUID nationalIdId, NationalIdRequestDto nationalIdRequestDto) {

        boolean existsCustomer = customerRepository.existsById(nationalIdRequestDto.getCustomerId());
        if(!existsCustomer){
            throw new IllegalArgumentException("ERROR: Invalid customer ID, please try again.");
        }

        CustomerUtils.validateIdentificationType(nationalIdRequestDto.getIdentificationType());

        NationalIdEntity nationalIdEntity = nationalIdRepository.findById(nationalIdId)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Identification not found with ID: %s"
                                , nationalIdId))
                );

        if(!ObjectUtils.isEmpty(nationalIdRequestDto.getIdNumber())){

            nationalIdEntity.setIdNumber(nationalIdRequestDto.getIdNumber());
        }

        if (!ObjectUtils.isEmpty(nationalIdRequestDto.getIssuanceDate())) {

            nationalIdEntity.setIssuanceDate(nationalIdRequestDto.getIssuanceDate());
        }

        if (!ObjectUtils.isEmpty(nationalIdRequestDto.getExpirationDate())) {

            nationalIdEntity.setExpirationDate(nationalIdRequestDto.getExpirationDate());
        }

        if(!ObjectUtils.isEmpty(nationalIdRequestDto.getIdentificationType())){
            //TODO 2: Si no valida, y lanza la excepción: ¿También va a cortar esta línea de flujo?
            CustomerUtils.validateIdentificationType(nationalIdRequestDto.getIdentificationType());
            nationalIdEntity.setIdentificationType(nationalIdRequestDto.getIdentificationType());
        }

        NationalIdEntity updatedNationalId = nationalIdRepository.save(nationalIdEntity);

        return NationalIdResponseDto.builder()
                .nationalIdId(nationalIdRequestDto.getNationalIdId())
                .idNumber(updatedNationalId.getIdNumber())
                .issuanceDate(updatedNationalId.getIssuanceDate())
                .expirationDate(updatedNationalId.getExpirationDate())
                .identificationType(updatedNationalId.getIdentificationType())
                .customerId(updatedNationalId.getCustomer().getCustomerId())
                .build();
    }

    @Override
    public void deleteNationalId(UUID nationalIdId) {
        NationalIdEntity nationalIdEntity = nationalIdRepository.findById(nationalIdId)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Identification not found with ID: %s"
                                , nationalIdId))
                );

        nationalIdRepository.delete(nationalIdEntity);
    }

    //todo 3: hacer el getAllNationalIds pero de un Cliente, no todos los de la base de datos. Sin paginado.
}
