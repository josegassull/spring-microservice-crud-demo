package com.crud_example.app.controller;

import com.crud_example.app.dto.request.CustomerRequestDto;
import com.crud_example.app.dto.response.CustomerResponseDto;
import com.crud_example.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponseDto = customerService.createCustomer(customerRequestDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable UUID customerId){
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    //Nota
    public ResponseEntity<Void> deleteCustomer(){
        return null;
    }
}
