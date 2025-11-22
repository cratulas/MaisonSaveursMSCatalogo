package com.saveurs.maison.catalog.service;

import com.google.cloud.Timestamp;
import com.saveurs.maison.catalog.domain.model.Cheese;
import com.saveurs.maison.catalog.domain.model.Wine;
import com.saveurs.maison.catalog.exception.NotFoundException;
import com.saveurs.maison.catalog.repository.FirestoreCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
}
