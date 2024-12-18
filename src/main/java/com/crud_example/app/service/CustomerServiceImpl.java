package com.crud_example.app.service;

import com.crud_example.core.entity.Customer;
import com.crud_example.core.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("customerService")
@RequiredArgsConstructor
//@NoArgsConstructor
//@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    /*@Autowired
    public CustomerServiceImpl(@Autowired CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }*/

    //Injection using Lombok
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer updateCustomer(UUID customerId, Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(UUID customerId) {
    }

}