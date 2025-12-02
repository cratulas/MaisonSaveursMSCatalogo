package com.saveurs.maison.catalog.dto;

public class CheeseForAiDto {

    private String id;
    private String name;
    private String milkType;   // cow, goat, sheep...
    private String style;      // soft, hard, blue...
    private String country;
    private String region;
    private Double price;      // opcional
    private boolean inStock;

    public CheeseForAiDto() {
    }

    public CheeseForAiDto(String id, String name, String milkType, String style,
                          String country, String region, Double price, boolean inStock) {
        this.id = id;
        this.name = name;
        this.milkType = milkType;
        this.style = style;
        this.country = country;
        this.region = region;
        this.price = price;
        this.inStock = inStock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
