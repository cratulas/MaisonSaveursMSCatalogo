package com.saveurs.maison.catalog.domain.model;

import com.google.cloud.Timestamp;
import com.saveurs.maison.catalog.domain.enums.WineFlavor;
import com.saveurs.maison.catalog.domain.enums.WineType;
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
public class Wine {

    private String id;

    private String nameEn;
    private String nameFr;

    private String descriptionEn;
    private String descriptionFr;

    private String imageUrl;

    private String origin;

    private String grape;

    private WineType type;

    private List<WineFlavor> flavors;

    private Integer sweetnessLevel;
    private Integer body;

    private BigDecimal price;

    private boolean available;

    private Double alcoholPercentage;

    private String servingTemperature;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
