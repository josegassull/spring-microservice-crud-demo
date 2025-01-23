package com.crud_example.app.controller;

import com.crud_example.app.dto.request.CustomerRequestDto;
import com.crud_example.app.dto.response.CustomerResponseDto;
import com.crud_example.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable UUID customerId) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto updatedCustomer = customerService.updateCustomer(
                customerRequestDto.getCustomerId() , customerRequestDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CustomerResponseDto> customerPage = customerService.getAllCustomers(page, size);
        return new ResponseEntity<>(customerPage, HttpStatus.OK);
    }
}
