package com.saveurs.maison.catalog.controller;

import com.saveurs.maison.catalog.dto.ApiResponse;
import com.saveurs.maison.catalog.dto.WineDto;
import com.saveurs.maison.catalog.mapper.WineMapper;
import com.saveurs.maison.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/wines")
@RequiredArgsConstructor
public class WineController {

    private final CatalogService service;
    private final WineMapper mapper;

    @GetMapping
    public ApiResponse<?> getAll() {
        return ApiResponse.ok(
                service.getAllWines()
                        .stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getById(@PathVariable String id) {
        return ApiResponse.ok(mapper.toDto(service.getWine(id)));
    }

    @PostMapping
    public ApiResponse<?> create(@RequestBody WineDto dto) {
        return ApiResponse.ok(
                mapper.toDto(service.createWine(mapper.fromDto(dto)))
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable String id, @RequestBody WineDto dto) {
        return ApiResponse.ok(
                mapper.toDto(service.updateWine(id, mapper.fromDto(dto)))
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable String id) {
        service.deleteWine(id);
        return ApiResponse.ok(null);
    }
}
