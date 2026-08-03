package io.delivhub.storeservice.services;

import io.delivhub.storeservice.dtos.request.product.CreateProductRequest;
import io.delivhub.storeservice.dtos.request.product.UpdateProductRequest;
import io.delivhub.storeservice.dtos.response.ProductResponse;
import io.delivhub.storeservice.entities.Category;
import io.delivhub.storeservice.entities.Product;
import io.delivhub.storeservice.exceptions.ResourceNotFoundException;
import io.delivhub.storeservice.mappers.ProductMapper;
import io.delivhub.storeservice.repositories.CategoryRepository;
import io.delivhub.storeservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductResponse getById(Long id) {
        return productMapper.toDto(findProductById(id));
    }

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    @Transactional
    public ProductResponse save(CreateProductRequest request) {
        Category category = findCategoryById(request.categoryId());
        Product product = productMapper.toEntity(request, category);
        return productMapper.toDto(productRepository.save(product));
    }

    @Transactional
    public ProductResponse update(Long id, UpdateProductRequest request) {
        Product product = findProductById(id);

        Category category = null;
        if (request.categoryId() != null) {
            category = findCategoryById(request.categoryId());
        }

        productMapper.updateProductFromDto(request, product, category);
        return productMapper.toDto(productRepository.save(product));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + id)
        );
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id: " + id)
        );
    }
}
