package com.example.publisher.service;

import com.example.publisher.domain.CustomerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final SQSEventPublisher publisher;

    @Autowired
    private ObjectMapper objectMapper;
    
    public void registerCustomer(CustomerDto customerDto) throws JsonProcessingException {
        publisher.publishEvent(objectMapper.writeValueAsString(customerDto), String.valueOf(customerDto.getCustomerId()));
    }
}
