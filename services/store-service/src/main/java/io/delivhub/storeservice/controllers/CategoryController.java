package io.delivhub.storeservice.controllers;

import io.delivhub.storeservice.dtos.request.category.CreateCategoryRequest;
import io.delivhub.storeservice.dtos.request.category.UpdateCategoryRequest;
import io.delivhub.storeservice.dtos.response.CategoryResponse;
import io.delivhub.storeservice.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Операции с категориями товаров")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @Operation(summary = "Создание категории")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Категория создана"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    @PostMapping
    public ResponseEntity<CategoryResponse> save(
            @Valid @RequestBody CreateCategoryRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.save(request));
    }

    @Operation(summary = "Обновление категории")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Категория обновлена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "404", description = "Категория не найдена")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryRequest request
    ) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @Operation(summary = "Удаление категории")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Категория удалена"),
            @ApiResponse(responseCode = "404", description = "Категория не найдена")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
