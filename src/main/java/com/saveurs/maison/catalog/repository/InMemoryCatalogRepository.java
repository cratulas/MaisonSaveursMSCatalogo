package com.saveurs.maison.catalog.repository;

import com.saveurs.maison.catalog.domain.model.Cheese;
import com.saveurs.maison.catalog.domain.model.Wine;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCatalogRepository {

    @Getter
    private final Map<String, Wine> wines = new ConcurrentHashMap<>();

    @Getter
    private final Map<String, Cheese> cheeses = new ConcurrentHashMap<>();

    // ------------------- Wines -------------------

    public Wine saveWine(Wine wine) {
        wines.put(wine.getId(), wine);
        return wine;
    }

    public Optional<Wine> findWineById(String id) {
        return Optional.ofNullable(wines.get(id));
    }

    public List<Wine> findAllWines() {
        return new ArrayList<>(wines.values());
    }

    public void deleteWine(String id) {
        wines.remove(id);
    }

    // ------------------- Cheeses -------------------

    public Cheese saveCheese(Cheese cheese) {
        cheeses.put(cheese.getId(), cheese);
        return cheese;
    }

    public Optional<Cheese> findCheeseById(String id) {
        return Optional.ofNullable(cheeses.get(id));
    }

    public List<Cheese> findAllCheeses() {
        return new ArrayList<>(cheeses.values());
    }

    public void deleteCheese(String id) {
        cheeses.remove(id);
    }
}
