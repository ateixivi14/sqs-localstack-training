package com.example.publisher.controller;

import com.example.publisher.domain.CustomerDto;
import com.example.publisher.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    
    private final CustomerService customerService;
    
    @PostMapping
    public void registerCustomer(@RequestBody CustomerDto customerDto) throws JsonProcessingException {
        customerService.registerCustomer(customerDto);
    }
}
