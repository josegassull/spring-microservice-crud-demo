package com.crud_example.app.service;

import com.crud_example.app.dto.error.CustomException;
import com.crud_example.app.dto.request.NationalIdRequestDto;
import com.crud_example.app.dto.response.NationalIdResponseDto;
import com.crud_example.core.entity.CustomerEntity;
import com.crud_example.core.entity.NationalIdEntity;
import com.crud_example.core.repository.CustomerRepository;
import com.crud_example.core.repository.NationalIdRepository;
import com.crud_example.core.utils.CustomerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("nationalIdService")
@RequiredArgsConstructor
public class NationalIdServiceImpl implements NationalIdService {

    private final NationalIdRepository nationalIdRepository;
    private final CustomerRepository customerRepository;

    @Override
    public NationalIdResponseDto createNationalId(NationalIdRequestDto nationalIdRequestDto) {

        boolean existsCustomer = customerRepository.existsById(nationalIdRequestDto.getCustomerId());

        if(!existsCustomer){
            throw new CustomException("Entity not found", HttpStatus.NOT_FOUND, String.format("ERROR: Customer with ID %s not found.", nationalIdRequestDto.getCustomerId()));
        }

        CustomerUtils.validateIdentificationType(nationalIdRequestDto.getIdentificationType());

        validateExistsNationalId(nationalIdRequestDto);

        NationalIdEntity nationalIdEntity = NationalIdEntity.builder()
                .idNumber(nationalIdRequestDto.getIdNumber())
                .issuanceDate(nationalIdRequestDto.getIssuanceDate())
                .expirationDate(nationalIdRequestDto.getExpirationDate())
                .identificationType(nationalIdRequestDto.getIdentificationType())
                .customer(CustomerEntity.builder().customerId(nationalIdRequestDto.getCustomerId()).build())
                .build();

        NationalIdEntity savedNationalIdEntity = nationalIdRepository.save(nationalIdEntity);

        return NationalIdResponseDto
                .builder()
                .nationalIdId(savedNationalIdEntity.getNationalIdId())
                .idNumber(savedNationalIdEntity.getIdNumber())
                .issuanceDate(savedNationalIdEntity.getIssuanceDate())
                .expirationDate(savedNationalIdEntity.getExpirationDate())
                .identificationType(savedNationalIdEntity.getIdentificationType())
                .customerId(savedNationalIdEntity.getCustomer().getCustomerId())
                .build();
    }

    private void validateExistsNationalId(NationalIdRequestDto nationalIdRequestDto) {
        validateDuplicatedNationalId(nationalIdRequestDto);
        validateSecondNationalId(nationalIdRequestDto);
    }

    private void validateDuplicatedNationalId(NationalIdRequestDto nationalIdRequestDto) {
        boolean existsNationalId = nationalIdRepository.existsByIdNumberAndIdentificationTypeAndCountry
                (nationalIdRequestDto.getIdNumber(),
                nationalIdRequestDto.getIdentificationType(), nationalIdRequestDto.getCountry());

        if(existsNationalId){
            throw new RuntimeException(String.format("ERROR: National ID %s already exists"
                    , nationalIdRequestDto.getIdNumber()));
        }
    }

    private void validateSecondNationalId(NationalIdRequestDto nationalIdRequestDto){
        boolean existsSecondNationalId = nationalIdRepository
                .existsByIdentificationTypeAndCountryAndCustomer_CustomerId(nationalIdRequestDto.getIdentificationType(),
                        nationalIdRequestDto.getCountry(),
                        nationalIdRequestDto.getCustomerId());

        if(existsSecondNationalId){
            throw new RuntimeException(String.format
                    ("ERROR: National ID type %s for country %s and customer %s already exists"
                    , nationalIdRequestDto.getIdentificationType(), nationalIdRequestDto.getCountry()
                    , nationalIdRequestDto.getCustomerId()));
        }
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
            throw new IllegalArgumentException(String.format("ERROR: Invalid customer ID %s, please try again."
                    , nationalIdRequestDto.getCustomerId()));
        }

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

    @Override
    public Set<NationalIdResponseDto> getAllNationalIdsByCustomerId(UUID customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(String.format("ERROR: Customer with ID %s not found.", customerId));
        }

        Set<NationalIdEntity> nationalIdEntities = nationalIdRepository.findAllByCustomerCustomerId(customerId);

        return nationalIdEntities.stream().map(nationalIdEntity -> NationalIdResponseDto.builder()
                .nationalIdId(nationalIdEntity.getNationalIdId())
                .idNumber(nationalIdEntity.getIdNumber())
                .issuanceDate(nationalIdEntity.getIssuanceDate())
                .expirationDate(nationalIdEntity.getExpirationDate())
                .identificationType(nationalIdEntity.getIdentificationType())
                .customerId(nationalIdEntity.getCustomer().getCustomerId())
                .build()).collect(Collectors.toSet());
    }
}
