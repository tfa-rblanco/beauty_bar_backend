package com.beautybar.service.customer;

import com.beautybar.dto.CustomerDto;
import com.beautybar.model.customer.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(CustomerDto customerDto);
    Customer updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
    List<Customer> searchCustomersByName(String name);
}