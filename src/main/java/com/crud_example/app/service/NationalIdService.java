package com.crud_example.app.service;

import com.crud_example.app.dto.request.NationalIdRequestDto;
import com.crud_example.app.dto.response.NationalIdResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NationalIdService {

    NationalIdResponseDto createNationalId(NationalIdRequestDto nationalIdRequestDto);

    NationalIdResponseDto getNationalId(Long idNumber);

    NationalIdResponseDto updateNationalId(Long idNumber, NationalIdRequestDto nationalIdRequestDto);

    void deleteNationalId(Long idNumber);

    Page<NationalIdResponseDto> getAllNationalIds(Pageable pageable);

}