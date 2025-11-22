package com.saveurs.maison.catalog.dto;

import com.saveurs.maison.catalog.domain.enums.CheeseFlavor;
import com.saveurs.maison.catalog.domain.enums.CheeseType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CheeseDto {

    private String id;

    private String nameEn;
    private String nameFr;

    private String descriptionEn;
    private String descriptionFr;

    private String imageUrl;
    private String origin;

    private CheeseType type;
    private List<CheeseFlavor> flavors;

    private int intensity;

    private BigDecimal price;
    private boolean available;
}
