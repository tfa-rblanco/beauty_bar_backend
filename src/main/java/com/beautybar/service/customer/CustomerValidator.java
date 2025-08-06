package com.beautybar.service.customer;

import com.beautybar.repository.customer.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    private final CustomerRepository customerRepository;

    public CustomerValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void validateEmailUniqueness(String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Customer with email " + email + " already exists");
        }
    }

    public void validateEmailUniquenessForUpdate(String currentEmail, String newEmail) {
        if (!currentEmail.equals(newEmail) && customerRepository.existsByEmail(newEmail)) {
            throw new IllegalArgumentException("Customer with email " + newEmail + " already exists");
        }
    }
}