package com.crud_example.app.service;

import com.crud_example.app.dto.request.CustomerRequestDto;
import com.crud_example.app.dto.response.CustomerResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto customer);

    CustomerResponseDto getCustomerById(UUID customerId);

    CustomerResponseDto updateCustomer(UUID customerId, CustomerRequestDto customerRequestDto);

    void deleteCustomer(UUID customerId);

    Page<CustomerResponseDto> getAllCustomers(int page, int size);
}