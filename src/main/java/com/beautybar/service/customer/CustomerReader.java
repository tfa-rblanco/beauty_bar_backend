package com.beautybar.service.customer;

import com.beautybar.model.customer.Customer;

import java.util.List;

public interface CustomerReader {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    List<Customer> searchCustomersByName(String name);
}