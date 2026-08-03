package io.delivhub.storeservice.controllers;

import io.delivhub.storeservice.dtos.request.product.CreateProductRequest;
import io.delivhub.storeservice.dtos.request.product.UpdateProductRequest;
import io.delivhub.storeservice.dtos.response.ProductResponse;
import io.delivhub.storeservice.services.ProductService;
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
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Операции с товарами")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @Operation(summary = "Создание продукта")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Продукт создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    @PostMapping
    public ResponseEntity<ProductResponse> save(
            @Valid @RequestBody CreateProductRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.save(request));
    }

    @Operation(summary = "Обновление продукта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Продукт обновлён"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request
    ) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    @Operation(summary = "Удаление продукта")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Продукт удалён"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}