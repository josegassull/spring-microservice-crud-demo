package com.crud_example.app.service;

import com.crud_example.app.dto.request.NationalIdRequestDto;
import com.crud_example.app.dto.response.CustomerResponseDto;
import com.crud_example.app.dto.response.NationalIdResponseDto;
import com.crud_example.core.entity.CustomerEntity;
import com.crud_example.core.entity.NationalIdEntity;
import com.crud_example.core.repository.NationalIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service("nationalIdService")
@RequiredArgsConstructor
public class NationalIdServiceImpl implements NationalIdService{

    private final NationalIdRepository nationalIdRepository;

    @Override
    public NationalIdResponseDto createNationalId(NationalIdRequestDto nationalIdRequestDto){

        NationalIdEntity nationalIdEntity = NationalIdEntity.builder().idNumber(nationalIdRequestDto.getIdNumber())
                .issuanceDate(nationalIdRequestDto.getIssuanceDate())
                .expirationDate(nationalIdRequestDto.getExpirationDate())
                .identificationType(nationalIdRequestDto.getIdentificationType())
                .build();

        NationalIdEntity savedNationalIdEntity = nationalIdRepository.save(nationalIdEntity);

        return NationalIdResponseDto
                .builder()
                .idNumber(savedNationalIdEntity.getIdNumber())
                .issuanceDate(savedNationalIdEntity.getIssuanceDate())
                .expirationDate(savedNationalIdEntity.getExpirationDate())
                .identificationType(savedNationalIdEntity.getIdentificationType())
                .build();
    }

    @Override
    public NationalIdResponseDto getNationalId(Long idNumber){
        NationalIdEntity nationalIdEntity = nationalIdRepository.findById(idNumber)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Customer not found with ID: %s", idNumber))
                );

        return NationalIdResponseDto
                .builder()
                .idNumber(nationalIdEntity.getIdNumber())
                .issuanceDate(nationalIdEntity.getIssuanceDate())
                .expirationDate(nationalIdEntity.getExpirationDate())
                .identificationType(nationalIdEntity.getIdentificationType())
                .build();
    }

    @Override
    public NationalIdResponseDto updateNationalId(Long idNumber, NationalIdRequestDto nationalIdRequestDto) {
        NationalIdEntity nationalIdEntity = nationalIdRepository.findById(idNumber)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Customer not found with ID: %s"
                                , idNumber))
                );

        if (!ObjectUtils.isEmpty(nationalIdRequestDto.getIssuanceDate())) {

            nationalIdEntity.setIssuanceDate(nationalIdRequestDto.getIssuanceDate());

        }

        if (!ObjectUtils.isEmpty(nationalIdRequestDto.getExpirationDate())) {

            nationalIdEntity.setExpirationDate(nationalIdRequestDto.getExpirationDate());

        }

        if (!ObjectUtils.isEmpty(nationalIdRequestDto.getIdentificationType())) {

            nationalIdEntity.setIdentificationType(nationalIdRequestDto.getIdentificationType());

        }

        NationalIdEntity updatedNationalId = nationalIdRepository.save(nationalIdEntity);

        return NationalIdResponseDto.builder()
                .idNumber(updatedNationalId.getIdNumber())
                .issuanceDate(updatedNationalId.getIssuanceDate())
                .expirationDate(updatedNationalId.getExpirationDate())
                .identificationType(updatedNationalId.getIdentificationType())
                .build();
    }

    @Override
    public void deleteNationalId(Long idNumber){
        NationalIdEntity nationalIdEntity = nationalIdRepository.findById(idNumber)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Customer not found with ID: %s"
                                , idNumber))
                );

        nationalIdRepository.delete(nationalIdEntity);
    }

    @Override
    public Page<NationalIdResponseDto> getAllNationalIds(Pageable pageable){
        Page<NationalIdEntity> nationalIdPage = nationalIdRepository.findAll(pageable);

        return nationalIdPage.map(nationalId -> NationalIdResponseDto.builder()
                .idNumber(nationalId.getIdNumber())
                .issuanceDate(nationalId.getIssuanceDate())
                .expirationDate(nationalId.getExpirationDate())
                .identificationType(nationalId.getIdentificationType())
                .build());
    }

}
