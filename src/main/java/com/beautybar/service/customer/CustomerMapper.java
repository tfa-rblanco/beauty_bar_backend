package com.beautybar.service.customer;

import com.beautybar.dto.CustomerDto;
import com.beautybar.model.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerDto dto) {
        return new Customer(dto.getName(), dto.getEmail(), dto.getPhone());
    }

    public void updateEntity(Customer entity, CustomerDto dto) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }
}