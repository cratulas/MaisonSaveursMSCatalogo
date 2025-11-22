package com.saveurs.maison.catalog.mapper;

import com.saveurs.maison.catalog.domain.model.Wine;
import com.saveurs.maison.catalog.dto.WineDto;
import org.springframework.stereotype.Component;

@Component
public class WineMapper {

    public WineDto toDto(Wine wine) {
        WineDto dto = new WineDto();
        dto.setId(wine.getId());
        dto.setNameEn(wine.getNameEn());
        dto.setNameFr(wine.getNameFr());
        dto.setDescriptionEn(wine.getDescriptionEn());
        dto.setDescriptionFr(wine.getDescriptionFr());
        dto.setImageUrl(wine.getImageUrl());
        dto.setOrigin(wine.getOrigin());
        dto.setGrape(wine.getGrape());
        dto.setType(wine.getType());
        dto.setFlavors(wine.getFlavors());
        dto.setSweetnessLevel(wine.getSweetnessLevel());
        dto.setBody(wine.getBody());
        dto.setPrice(wine.getPrice());
        dto.setAvailable(wine.isAvailable());
        dto.setAlcoholPercentage(wine.getAlcoholPercentage());
        dto.setServingTemperature(wine.getServingTemperature());
        return dto;
    }

    public Wine fromDto(WineDto dto) {
        return Wine.builder()
                .id(dto.getId())
                .nameEn(dto.getNameEn())
                .nameFr(dto.getNameFr())
                .descriptionEn(dto.getDescriptionEn())
                .descriptionFr(dto.getDescriptionFr())
                .imageUrl(dto.getImageUrl())
                .origin(dto.getOrigin())
                .grape(dto.getGrape())
                .type(dto.getType())
                .flavors(dto.getFlavors())
                .sweetnessLevel(dto.getSweetnessLevel())
                .body(dto.getBody())
                .price(dto.getPrice())
                .available(dto.isAvailable())
                .alcoholPercentage(dto.getAlcoholPercentage())
                .servingTemperature(dto.getServingTemperature())
                .build();
    }
}
