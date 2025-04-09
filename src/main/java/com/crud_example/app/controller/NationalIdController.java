package com.crud_example.app.controller;
import com.crud_example.app.dto.request.NationalIdRequestDto;
import com.crud_example.app.dto.response.NationalIdResponseDto;
import com.crud_example.app.service.NationalIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/nationalId")
@RequiredArgsConstructor
public class NationalIdController {

    private final NationalIdService nationalIdService;

    @PostMapping
    public ResponseEntity<NationalIdResponseDto> createNationalId(@RequestBody NationalIdRequestDto
                                                                              nationalIdRequestDto) {
        NationalIdResponseDto nationalIdResponseDto = nationalIdService.createNationalId(nationalIdRequestDto);
        return new ResponseEntity<>(nationalIdResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("{nationalIdId}")
    public ResponseEntity<NationalIdResponseDto> getNationalIdById(@PathVariable UUID nationalIdId){
        NationalIdResponseDto nationalIdResponseDto = nationalIdService.getNationalId(nationalIdId);
        return new ResponseEntity<>(nationalIdResponseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<NationalIdResponseDto> updateNationalId(@RequestBody NationalIdRequestDto nationalIdRequestDto){
        NationalIdResponseDto nationalIdResponseDto = nationalIdService.updateNationalId
                (nationalIdRequestDto.getNationalIdId(), nationalIdRequestDto);
        return new ResponseEntity<>(nationalIdResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("{nationalIdId}")
    public ResponseEntity<Void> deleteNationalId(@PathVariable UUID nationalIdId){
        nationalIdService.deleteNationalId(nationalIdId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list/{customerId}")
    public ResponseEntity<Set<NationalIdResponseDto>> getAllNationalIdsByCustomer(@PathVariable UUID customerId) {
        Set<NationalIdResponseDto> nationalIds = nationalIdService.getAllNationalIdsByCustomerId(customerId);
        return new ResponseEntity<>(nationalIds, HttpStatus.OK);
    }
}