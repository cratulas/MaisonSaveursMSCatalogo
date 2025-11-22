package com.saveurs.maison.catalog.domain.model;

import com.google.cloud.Timestamp;
import com.saveurs.maison.catalog.domain.enums.CheeseFlavor;
import com.saveurs.maison.catalog.domain.enums.CheeseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cheese {

    private String id;

    private String nameEn;
    private String nameFr;

    private String descriptionEn;
    private String descriptionFr;

    private String imageUrl;

    private String origin;

    private CheeseType type;

    private List<CheeseFlavor> flavors;

    private Integer intensity;

    private BigDecimal price;

    private boolean available;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
