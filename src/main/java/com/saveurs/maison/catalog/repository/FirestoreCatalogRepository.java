package com.saveurs.maison.catalog.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.saveurs.maison.catalog.domain.model.Cheese;
import com.saveurs.maison.catalog.domain.model.Wine;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FirestoreCatalogRepository {

    private final Firestore firestore;

    @Value("${firebase.firestore.collection.wines}")
    private String winesCollection;

    @Value("${firebase.firestore.collection.cheeses}")
    private String cheesesCollection;

    // ------------------- Wines -------------------

    public Wine saveWine(Wine wine) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future =
                firestore.collection(winesCollection)
                        .document(wine.getId())
                        .set(wine);

        future.get();
        return wine;
    }

    public Wine findWineById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot doc =
                firestore.collection(winesCollection)
                        .document(id)
                        .get()
                        .get();

        return doc.exists() ? doc.toObject(Wine.class) : null;
    }

    public List<Wine> findAllWines() throws ExecutionException, InterruptedException {
        return firestore.collection(winesCollection)
                .get()
                .get()
                .getDocuments()
                .stream()
                .map(d -> d.toObject(Wine.class))
                .collect(Collectors.toList());
    }

    public void deleteWine(String id) throws ExecutionException, InterruptedException {
        firestore.collection(winesCollection)
                .document(id)
                .delete()
                .get();
    }

    // ------------------- Cheeses -------------------

    public Cheese saveCheese(Cheese cheese) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future =
                firestore.collection(cheesesCollection)
                        .document(cheese.getId())
                        .set(cheese);

        future.get();
        return cheese;
    }

    public Cheese findCheeseById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot doc =
                firestore.collection(cheesesCollection)
                        .document(id)
                        .get()
                        .get();

        return doc.exists() ? doc.toObject(Cheese.class) : null;
    }

    public List<Cheese> findAllCheeses() throws ExecutionException, InterruptedException {
        return firestore.collection(cheesesCollection)
                .get()
                .get()
                .getDocuments()
                .stream()
                .map(d -> d.toObject(Cheese.class))
                .collect(Collectors.toList());
    }

    public void deleteCheese(String id) throws ExecutionException, InterruptedException {
        firestore.collection(cheesesCollection)
                .document(id)
                .delete()
                .get();
    }
}
