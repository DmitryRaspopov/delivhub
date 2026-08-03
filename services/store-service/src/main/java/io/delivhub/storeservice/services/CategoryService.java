package io.delivhub.storeservice.services;

import io.delivhub.storeservice.dtos.request.category.CreateCategoryRequest;
import io.delivhub.storeservice.dtos.request.category.UpdateCategoryRequest;
import io.delivhub.storeservice.dtos.response.CategoryResponse;
import io.delivhub.storeservice.entities.Category;
import io.delivhub.storeservice.exceptions.ResourceNotFoundException;
import io.delivhub.storeservice.mappers.CategoryMapper;
import io.delivhub.storeservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse getById(Long id) {
        return categoryMapper.toDto(findCategoryById(id));
    }

    public Page<CategoryResponse> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryMapper::toDto);
    }

    @Transactional
    public CategoryResponse save(CreateCategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponse update(Long id, UpdateCategoryRequest request) {
        Category category = findCategoryById(id);
        categoryMapper.updateCategoryFromDto(request, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id: " + id)
        );
    }
}
