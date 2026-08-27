package io.delivhub.customerservice.mappers;

import io.delivhub.customerservice.dtos.request.city.CreateCityRequest;
import io.delivhub.customerservice.dtos.request.city.UpdateCityRequest;
import io.delivhub.customerservice.dtos.response.CityResponse;
import io.delivhub.customerservice.entities.City;
import io.delivhub.customerservice.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CityMapper {

    @Mapping(target = "customerIds", source = "customers")
    CityResponse toDto(City city);

    City toEntity(CreateCityRequest dto);

    City updateCityFromDto(UpdateCityRequest dto, @MappingTarget City city);

    default UUID mapCustomerToId(Customer customer) {
        return customer.getId();
    }
}
