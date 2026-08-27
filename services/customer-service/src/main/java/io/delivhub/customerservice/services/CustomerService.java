package io.delivhub.customerservice.services;

import io.delivhub.customerservice.dtos.request.customer.CreateCustomerRequest;
import io.delivhub.customerservice.dtos.request.customer.UpdateCustomerRequest;
import io.delivhub.customerservice.dtos.response.CustomerResponse;
import io.delivhub.customerservice.entities.City;
import io.delivhub.customerservice.entities.Customer;
import io.delivhub.customerservice.exceptions.ResourceNotFoundException;
import io.delivhub.customerservice.mappers.CustomerMapper;
import io.delivhub.customerservice.repositories.CityRepository;
import io.delivhub.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CityRepository cityRepository;
    private final CustomerMapper mapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CustomerResponse getById(UUID id) {
        return mapper.toDto(findCustomerById(id));
    }

    public Page<CustomerResponse> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).map(mapper::toDto);
    }

    @Transactional
    public CustomerResponse save(CreateCustomerRequest request) {
        City city = findCityById(request.cityId());
        Customer customer = mapper.toEntity(request, city);
        return mapper.toDto(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponse update(UUID customerId, UpdateCustomerRequest request) {
        Customer customerToSave = findCustomerById(customerId);
        City city = request.cityId() != null
                ? findCityById(request.cityId())
                : null;

        mapper.updateCustomerFromDto(request, customerToSave, city);
        return mapper.toDto(customerToSave);
    }

    @Transactional
    public void deleteById(UUID id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
    }

    private Customer findCustomerById(UUID id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer with id " + id + " not found")
        );
    }

    private City findCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("City with id " + id + " not found")
        );
    }
}
