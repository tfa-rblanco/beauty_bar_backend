package com.beautybar.controller;

import com.beautybar.dto.ApiResponse;
import com.beautybar.dto.CustomerDto;
import com.beautybar.model.customer.Customer;
import com.beautybar.service.customer.CustomerReader;
import com.beautybar.service.customer.CustomerWriter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerReader customerReader;
    private final CustomerWriter customerWriter;

    public CustomerController(CustomerReader customerReader, CustomerWriter customerWriter) {
        this.customerReader = customerReader;
        this.customerWriter = customerWriter;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        List<Customer> customers = customerReader.getAllCustomers();
        return ResponseEntity.ok(ApiResponse.success("Customers retrieved successfully", customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable Long id) {
        Customer customer = customerReader.getCustomerById(id);
        return ResponseEntity.ok(ApiResponse.success("Customer retrieved successfully", customer));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        Customer createdCustomer = customerWriter.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Customer created successfully", createdCustomer));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) {
        Customer updatedCustomer = customerWriter.updateCustomer(id, customerDto);
        return ResponseEntity.ok(ApiResponse.success("Customer updated successfully", updatedCustomer));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Object>> deleteCustomer(@PathVariable Long id) {
        customerWriter.deleteCustomer(id);
        return ResponseEntity.ok(ApiResponse.success("Customer deleted successfully", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Customer>>> searchCustomers(@RequestParam String name) {
        List<Customer> customers = customerReader.searchCustomersByName(name);
        return ResponseEntity.ok(ApiResponse.success("Customers found successfully", customers));
    }
}