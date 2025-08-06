package com.beautybar.service.customer;

import com.beautybar.dto.CustomerDto;
import com.beautybar.exception.ResourceNotFoundException;
import com.beautybar.model.customer.Customer;
import com.beautybar.repository.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService implements CustomerServiceInterface, CustomerReader, CustomerWriter {

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, 
                          CustomerValidator customerValidator,
                          CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerValidator = customerValidator;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        customerValidator.validateEmailUniqueness(customerDto.getEmail());
        Customer customer = customerMapper.toEntity(customerDto);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = getCustomerById(id);
        customerValidator.validateEmailUniquenessForUpdate(existingCustomer.getEmail(), customerDto.getEmail());
        customerMapper.updateEntity(existingCustomer, customerDto);
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> searchCustomersByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
}