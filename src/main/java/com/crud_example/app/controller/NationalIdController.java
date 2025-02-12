package com.crud_example.app.controller;
import com.crud_example.app.dto.request.NationalIdRequestDto;
import com.crud_example.app.dto.response.NationalIdResponseDto;
import com.crud_example.app.service.NationalIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
