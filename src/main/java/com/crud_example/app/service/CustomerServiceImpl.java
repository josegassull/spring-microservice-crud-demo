package com.crud_example.app.service;

import com.crud_example.app.dto.error.CustomException;
import com.crud_example.app.dto.request.CustomerRequestDto;
import com.crud_example.app.dto.response.CustomerResponseDto;
import com.crud_example.core.entity.CustomerEntity;
import com.crud_example.core.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service("customerService")
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        CustomerEntity customerEntity = CustomerEntity
                .builder()
                .age(customerRequestDto.getAge())
                .name(customerRequestDto.getName())
                .build();
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        return CustomerResponseDto
                .builder().customerId(savedCustomer.getCustomerId())
                .age(savedCustomer.getAge())
                .name(savedCustomer.getName())
                .build();
    }

    @Override
    public CustomerResponseDto getCustomerById(UUID customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomException(
                                String.format("Customer not found with ID: %s", customerId),
                                HttpStatus.NOT_FOUND,
                                "The customer you're trying to retrieve does not exist"
                        )
                );

        return CustomerResponseDto
                .builder()
                .customerId(customerEntity.getCustomerId())
                .age(customerEntity.getAge())
                .name(customerEntity.getName())
                .build();
    }

    @Override
    public CustomerResponseDto updateCustomer(UUID customerId, CustomerRequestDto customerRequestDto) {

        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomException(
                                String.format("Customer not found with ID: %s", customerId),
                                HttpStatus.NOT_FOUND,
                                "The customer you're trying to update does not exist"
                        )
                );

        if (!ObjectUtils.isEmpty(customerRequestDto.getAge())) {

            customerEntity.setAge(customerRequestDto.getAge());

        }

        if (!ObjectUtils.isEmpty(customerRequestDto.getName())) {

            customerEntity.setName(customerRequestDto.getName());

        }

        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        return CustomerResponseDto
                .builder()
                .customerId(savedCustomer.getCustomerId())
                .age(savedCustomer.getAge())
                .name(savedCustomer.getName())
                .build();
    }

    @Override
    public void deleteCustomer(UUID customerId) {

        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomException(
                                String.format("Customer not found with ID: %s", customerId),
                                HttpStatus.NOT_FOUND,
                                "The customer you're trying to delete does not exist"
                        )
                );

        customerRepository.delete(customerEntity);

    }

    @Override
    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {

        Page<CustomerEntity> customerPage = customerRepository.findAll(pageable);

        return customerPage.map(customer -> CustomerResponseDto.builder()
                .customerId(customer.getCustomerId())
                .age(customer.getAge())
                .name(customer.getName())
                .build());
    }
}