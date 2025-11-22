package com.saveurs.maison.catalog.controller;

import com.saveurs.maison.catalog.dto.ApiResponse;
import com.saveurs.maison.catalog.dto.CheeseDto;
import com.saveurs.maison.catalog.mapper.CheeseMapper;
import com.saveurs.maison.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cheeses")
@RequiredArgsConstructor
public class CheeseController {

    private final CatalogService service;
    private final CheeseMapper mapper;

    @GetMapping
    public ApiResponse<?> getAll() {
        return ApiResponse.ok(
                service.getAllCheeses()
                        .stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getById(@PathVariable String id) {
        return ApiResponse.ok(
                mapper.toDto(service.getCheese(id))
        );
    }

    @PostMapping
    public ApiResponse<?> create(@RequestBody CheeseDto dto) {
        return ApiResponse.ok(
                mapper.toDto(service.createCheese(mapper.fromDto(dto)))
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable String id, @RequestBody CheeseDto dto) {
        return ApiResponse.ok(
                mapper.toDto(service.updateCheese(id, mapper.fromDto(dto)))
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable String id) {
        service.deleteCheese(id);
        return ApiResponse.ok(null);
    }
}
