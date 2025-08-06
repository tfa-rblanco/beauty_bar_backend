package com.beautybar.config;

import com.beautybar.service.customer.CustomerReader;
import com.beautybar.service.customer.CustomerServiceInterface;
import com.beautybar.service.customer.CustomerWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CustomerReader customerReader(CustomerServiceInterface customerService) {
        return (CustomerReader) customerService;
    }

    @Bean
    public CustomerWriter customerWriter(CustomerServiceInterface customerService) {
        return (CustomerWriter) customerService;
    }
}