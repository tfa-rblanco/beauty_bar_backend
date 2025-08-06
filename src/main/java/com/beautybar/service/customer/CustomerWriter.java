package com.beautybar.service.customer;

import com.beautybar.dto.CustomerDto;
import com.beautybar.model.customer.Customer;

public interface CustomerWriter {
    Customer createCustomer(CustomerDto customerDto);
    Customer updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
}