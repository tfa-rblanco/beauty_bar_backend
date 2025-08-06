package com.beautybar.service;

import com.beautybar.dto.CustomerDto;
import com.beautybar.model.customer.Customer;
import com.beautybar.repository.customer.CustomerRepository;
import com.beautybar.service.customer.CustomerMapper;
import com.beautybar.service.customer.CustomerService;
import com.beautybar.service.customer.CustomerValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerValidator customerValidator;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void createCustomer_ShouldFollowSRP() {
        // Given
        CustomerDto dto = new CustomerDto("John", "john@test.com", "123456789");
        Customer customer = new Customer("John", "john@test.com", "123456789");
        
        when(customerMapper.toEntity(dto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

        // When
        Customer result = customerService.createCustomer(dto);

        // Then
        verify(customerValidator).validateEmailUniqueness(dto.getEmail());
        verify(customerMapper).toEntity(dto);
        verify(customerRepository).save(customer);
        assertEquals(customer, result);
    }
}