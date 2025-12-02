package com.saveurs.maison.catalog.service;

import com.google.cloud.Timestamp;
import com.saveurs.maison.catalog.domain.model.Cheese;
import com.saveurs.maison.catalog.domain.model.Wine;
import com.saveurs.maison.catalog.dto.CheeseForAiDto;
import com.saveurs.maison.catalog.dto.WineForAiDto;
import com.saveurs.maison.catalog.exception.NotFoundException;
import com.saveurs.maison.catalog.repository.FirestoreCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final FirestoreCatalogRepository firestoreRepository;

    // ------------------- Wines -------------------

    public Wine createWine(Wine wine) {
        wine.setId(UUID.randomUUID().toString());
        wine.setCreatedAt(Timestamp.now());
        wine.setUpdatedAt(Timestamp.now());
        try {
            return firestoreRepository.saveWine(wine);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error saving wine to Firestore", e);
        }
    }

    public Wine getWine(String id) {
        try {
            Wine wine = firestoreRepository.findWineById(id);
            if (wine == null) {
                throw new NotFoundException("Wine not found");
            }
            return wine;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error reading wine from Firestore", e);
        }
    }

    public List<Wine> getAllWines() {
        try {
            return firestoreRepository.findAllWines();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error reading wines from Firestore", e);
        }
    }

    public Wine updateWine(String id, Wine updated) {
        Wine existing = getWine(id);
        updated.setId(id);
        updated.setCreatedAt(existing.getCreatedAt());
        updated.setUpdatedAt(Timestamp.now());
        try {
            return firestoreRepository.saveWine(updated);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error updating wine in Firestore", e);
        }
    }

    public void deleteWine(String id) {
        getWine(id);
        try {
            firestoreRepository.deleteWine(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error deleting wine from Firestore", e);
        }
    }

    // ------------------- Cheeses -------------------

    public Cheese createCheese(Cheese cheese) {
        cheese.setId(UUID.randomUUID().toString());
        cheese.setCreatedAt(Timestamp.now());
        cheese.setUpdatedAt(Timestamp.now());
        try {
            return firestoreRepository.saveCheese(cheese);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error saving cheese to Firestore", e);
        }
    }

    public Cheese getCheese(String id) {
        try {
            Cheese cheese = firestoreRepository.findCheeseById(id);
            if (cheese == null) {
                throw new NotFoundException("Cheese not found");
            }
            return cheese;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error reading cheese from Firestore", e);
        }
    }

    public List<Cheese> getAllCheeses() {
        try {
            return firestoreRepository.findAllCheeses();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error reading cheeses from Firestore", e);
        }
    }

    public Cheese updateCheese(String id, Cheese updated) {
        Cheese existing = getCheese(id);
        updated.setId(id);
        updated.setCreatedAt(existing.getCreatedAt());
        updated.setUpdatedAt(Timestamp.now());
        try {
            return firestoreRepository.saveCheese(updated);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error updating cheese in Firestore", e);
        }
    }

    public void deleteCheese(String id) {
        getCheese(id);
        try {
            firestoreRepository.deleteCheese(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error deleting cheese from Firestore", e);
        }
    }

    // ------------------- IA helpers -------------------

    /**
     * Devuelve una lista de vinos resumidos para IA.
     * Si inStockOnly = true, filtra solo los que estén en stock.
     */
    public List<WineForAiDto> getWinesForAi(boolean inStockOnly) {
        List<Wine> wines = getAllWines();

        return wines.stream()
                .filter(w -> !inStockOnly || isInStock(w))
                .map(this::mapToWineForAiDto)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve una lista de quesos resumidos para IA.
     * Si inStockOnly = true, filtra solo los que estén en stock.
     */
    public List<CheeseForAiDto> getCheesesForAi(boolean inStockOnly) {
        List<Cheese> cheeses = getAllCheeses();

        return cheeses.stream()
                .filter(c -> !inStockOnly || isInStock(c))
                .map(this::mapToCheeseForAiDto)
                .collect(Collectors.toList());
    }

    // --- mapeos y helpers privados ---

    private boolean isInStock(Wine wine) {
        // Por ahora usamos el flag available del modelo
        return wine.isAvailable();
    }

    private boolean isInStock(Cheese cheese) {
        // Igual para cheese
        return cheese.isAvailable();
    }

    private WineForAiDto mapToWineForAiDto(Wine wine) {
        String id = wine.getId();

        // Preferimos nombre en inglés, luego francés, y si no hay, el id
        String name = wine.getNameEn() != null && !wine.getNameEn().isBlank()
                ? wine.getNameEn()
                : (wine.getNameFr() != null && !wine.getNameFr().isBlank()
                    ? wine.getNameFr()
                    : wine.getId());

        // getType() devuelve un enum WineType -> lo convertimos a String
        String type = wine.getType() != null ? wine.getType().name() : null;

        // Podemos usar los flavors como "estilo"
        String style = (wine.getFlavors() != null && !wine.getFlavors().isEmpty())
                ? wine.getFlavors().toString()
                : null;

        // origin lo usamos como "country"
        String country = wine.getOrigin();
        String region  = null; // tu modelo no tiene región separada

        // getPrice() es BigDecimal -> Double
        Double price = wine.getPrice() != null ? wine.getPrice().doubleValue() : null;

        boolean inStock = isInStock(wine);

        return new WineForAiDto(id, name, type, style, country, region, price, inStock);
    }

    private CheeseForAiDto mapToCheeseForAiDto(Cheese cheese) {
        String id = cheese.getId();

        // Igual: preferimos nameEn, luego nameFr, luego id
        String name = cheese.getNameEn() != null && !cheese.getNameEn().isBlank()
                ? cheese.getNameEn()
                : (cheese.getNameFr() != null && !cheese.getNameFr().isBlank()
                    ? cheese.getNameFr()
                    : cheese.getId());

        // CheeseType enum -> String (e.g. SOFT, HARD, BLUE)
        String milkType = cheese.getType() != null ? cheese.getType().name() : null;

        // Flavors como estilo
        String style = (cheese.getFlavors() != null && !cheese.getFlavors().isEmpty())
                ? cheese.getFlavors().toString()
                : null;

        String country = cheese.getOrigin();
        String region  = null; // no existe en tu modelo

        Double price = cheese.getPrice() != null ? cheese.getPrice().doubleValue() : null;

        boolean inStock = isInStock(cheese);

        return new CheeseForAiDto(id, name, milkType, style, country, region, price, inStock);
    }
}
