package com.crud_example.app.service;

import com.crud_example.app.dto.request.NationalIdRequestDto;
import com.crud_example.app.dto.response.NationalIdResponseDto;

import java.util.UUID;

public interface NationalIdService {

    NationalIdResponseDto createNationalId(NationalIdRequestDto nationalIdRequestDto);

    NationalIdResponseDto getNationalId(UUID nationalIdId);

    NationalIdResponseDto updateNationalId(UUID nationalIdId, NationalIdRequestDto nationalIdRequestDto);

    void deleteNationalId(UUID idKey);

}