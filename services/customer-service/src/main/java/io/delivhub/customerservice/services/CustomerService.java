package io.delivhub.customerservice.services;

import io.delivhub.customerservice.dtos.response.CustomerResponse;
import io.delivhub.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CustomerResponse getById(Long id) {
        return new CustomerResponse(); // TODO: доделать
    }
}
