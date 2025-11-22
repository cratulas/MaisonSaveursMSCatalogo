package com.saveurs.maison.catalog.mapper;

import com.saveurs.maison.catalog.domain.model.Cheese;
import com.saveurs.maison.catalog.dto.CheeseDto;
import org.springframework.stereotype.Component;

@Component
public class CheeseMapper {

    public CheeseDto toDto(Cheese cheese) {
        CheeseDto dto = new CheeseDto();
        dto.setId(cheese.getId());
        dto.setNameEn(cheese.getNameEn());
        dto.setNameFr(cheese.getNameFr());
        dto.setDescriptionEn(cheese.getDescriptionEn());
        dto.setDescriptionFr(cheese.getDescriptionFr());
        dto.setImageUrl(cheese.getImageUrl());
        dto.setOrigin(cheese.getOrigin());
        dto.setType(cheese.getType());
        dto.setFlavors(cheese.getFlavors());
        dto.setIntensity(cheese.getIntensity());
        dto.setPrice(cheese.getPrice());
        dto.setAvailable(cheese.isAvailable());
        return dto;
    }

    public Cheese fromDto(CheeseDto dto) {
        return Cheese.builder()
                .id(dto.getId())
                .nameEn(dto.getNameEn())
                .nameFr(dto.getNameFr())
                .descriptionEn(dto.getDescriptionEn())
                .descriptionFr(dto.getDescriptionFr())
                .imageUrl(dto.getImageUrl())
                .origin(dto.getOrigin())
                .type(dto.getType())
                .flavors(dto.getFlavors())
                .intensity(dto.getIntensity())
                .price(dto.getPrice())
                .available(dto.isAvailable())
                .build();
    }
}
