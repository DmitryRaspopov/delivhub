package io.delivhub.customerservice.mappers;

import io.delivhub.customerservice.dtos.request.customer.CreateCustomerRequest;
import io.delivhub.customerservice.dtos.request.customer.UpdateCustomerRequest;
import io.delivhub.customerservice.dtos.response.CustomerResponse;
import io.delivhub.customerservice.entities.City;
import io.delivhub.customerservice.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

    @Mapping(target = "cityId", source = "city.id")
    CustomerResponse toDto(Customer customer);

    Customer toEntity(CreateCustomerRequest dto, City city);

    Customer updateCustomerFromDto(
            UpdateCustomerRequest dto,
            @MappingTarget Customer customer,
            City city
    );
}
