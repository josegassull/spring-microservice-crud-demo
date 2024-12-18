package com.crud_example.app.service;

import com.crud_example.core.entity.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer getCustomerById(UUID customerId);

    List<Customer> getAllCustomers();

    Customer updateCustomer(UUID customerId, Customer customer);

    void deleteCustomer(UUID customerId);
}