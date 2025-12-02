package com.saveurs.maison.catalog.dto;

public class WineForAiDto {

    private String id;
    private String name;
    private String type;       // red, white, rosé, sparkling...
    private String style;      // fruity, full-bodied, etc.
    private String country;
    private String region;
    private Double price;      // opcional
    private boolean inStock;

    public WineForAiDto() {
    }

    public WineForAiDto(String id, String name, String type, String style,
                        String country, String region, Double price, boolean inStock) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
