package io.delivhub.storeservice.mappers;

import io.delivhub.storeservice.dtos.request.category.CreateCategoryRequest;
import io.delivhub.storeservice.dtos.request.category.UpdateCategoryRequest;
import io.delivhub.storeservice.dtos.response.CategoryResponse;
import io.delivhub.storeservice.entities.Category;
import io.delivhub.storeservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {

    @Mapping(target = "productIds", source = "products")
    CategoryResponse toDto(Category category);

    Category toEntity(CreateCategoryRequest dto);

    Category updateCategoryFromDto(UpdateCategoryRequest dto, @MappingTarget Category category);

    default Long mapProductToId(Product product) {
        return product == null ? null : product.getId();
    }
}
