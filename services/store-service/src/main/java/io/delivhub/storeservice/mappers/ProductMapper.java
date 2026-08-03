package io.delivhub.storeservice.mappers;

import io.delivhub.storeservice.dtos.request.product.CreateProductRequest;
import io.delivhub.storeservice.dtos.request.product.DimensionsRequest;
import io.delivhub.storeservice.dtos.request.product.UpdateProductRequest;
import io.delivhub.storeservice.dtos.response.DimensionsResponse;
import io.delivhub.storeservice.dtos.response.ProductResponse;
import io.delivhub.storeservice.entities.Category;
import io.delivhub.storeservice.entities.Dimensions;
import io.delivhub.storeservice.entities.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toDto(Product product);

    DimensionsResponse toDtoDimensions(Dimensions dimensions);

    List<ProductResponse> toDtoList(List<Product> products);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    Product toEntity(CreateProductRequest dto, Category category);

    Dimensions toEntityDimensions(DimensionsRequest dimensionsRequest);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    Product updateProductFromDto(
            UpdateProductRequest dto,
            @MappingTarget Product product,
            Category category
    );
}