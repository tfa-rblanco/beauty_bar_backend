package com.beautybar.service.customer;

import com.beautybar.repository.customer.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceFactory {

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;
    private final CustomerMapper customerMapper;

    public CustomerServiceFactory(CustomerRepository customerRepository,
                                 CustomerValidator customerValidator,
                                 CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerValidator = customerValidator;
        this.customerMapper = customerMapper;
    }

    public CustomerServiceInterface createCustomerService() {
        return new CustomerService(customerRepository, customerValidator, customerMapper);
    }
}