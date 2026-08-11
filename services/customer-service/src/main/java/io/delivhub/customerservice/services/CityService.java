package io.delivhub.customerservice.services;

import io.delivhub.customerservice.dtos.request.city.CreateCityRequest;
import io.delivhub.customerservice.dtos.request.city.UpdateCityRequest;
import io.delivhub.customerservice.dtos.response.CityResponse;
import io.delivhub.customerservice.entities.City;
import io.delivhub.customerservice.exceptions.ResourceNotFoundException;
import io.delivhub.customerservice.mappers.CityMapper;
import io.delivhub.customerservice.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class CityService {
    private final CityRepository repository;
    private final CityMapper mapper;

    public CityResponse getById(Long id) {
        return mapper.toDto(findCityById(id));
    }

    public Page<CityResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Transactional
    public CityResponse save(CreateCityRequest request) {
        City city = mapper.toEntity(request);
        return mapper.toDto(repository.save(city));
    }

    @Transactional
    public CityResponse update(Long id, UpdateCityRequest request) {
        City city = findCityById(id);
        mapper.updateCityFromDto(request, city);
        return mapper.toDto(repository.save(city));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("City with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    private City findCityById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("City with id " + id + " not found")
        );
    }
}
