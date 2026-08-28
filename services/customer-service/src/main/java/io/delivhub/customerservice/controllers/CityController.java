package io.delivhub.customerservice.controllers;

import io.delivhub.customerservice.dtos.request.city.CreateCityRequest;
import io.delivhub.customerservice.dtos.request.city.UpdateCityRequest;
import io.delivhub.customerservice.dtos.response.CityResponse;
import io.delivhub.customerservice.services.CityService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
@Tag(name = "Cities", description = "Операции с городами клиентов")
public class CityController {
    private final CityService cityService;

    @Operation(summary = "Получение города по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Город найден"),
            @ApiResponse(responseCode = "404", description = "Город не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @Operation(summary = "Получение списка всех городов с пагинацией")
    @ApiResponse(responseCode = "200", description = "Список успешно получен")
    @GetMapping
    public ResponseEntity<Page<CityResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(cityService.findAll(pageable));
    }

    @Operation(summary = "Создание города")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Город создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    @PostMapping
    public ResponseEntity<CityResponse> save(
            @Valid @RequestBody CreateCityRequest request
    ) {

        CityResponse savedCity = cityService.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCity.id())
                .toUri();

        return ResponseEntity.created(location).body(savedCity);
    }


    @Operation(summary = "Обновление города")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Город обновлён"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "404", description = "Город не найден")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CityResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCityRequest request
    ) {
        return ResponseEntity.ok(cityService.update(id, request));
    }

    @Operation(summary = "Удаление города")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Город удалён"),
            @ApiResponse(responseCode = "404", description = "Город не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
