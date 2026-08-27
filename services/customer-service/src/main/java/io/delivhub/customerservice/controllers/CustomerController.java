package io.delivhub.customerservice.controllers;

import io.delivhub.customerservice.dtos.request.customer.CreateCustomerRequest;
import io.delivhub.customerservice.dtos.request.customer.UpdateCustomerRequest;
import io.delivhub.customerservice.dtos.response.CustomerResponse;
import io.delivhub.customerservice.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Операции с клиентами")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(customerService.findAll(pageable));
    }

    @Operation(summary = "Создание клиента")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Клиент создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    @PostMapping
    public ResponseEntity<CustomerResponse> save(
            @Valid @RequestBody CreateCustomerRequest request
    ) {

        CustomerResponse savedCustomer = customerService.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.id())
                .toUri();

        return ResponseEntity.created(location).body(savedCustomer);
    }

    @Operation(summary = "Обновление клиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Клиент обновлён"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.ok(customerService.update(id, request));
    }

    @Operation(summary = "Удаление клиента")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Клиент удалён"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
