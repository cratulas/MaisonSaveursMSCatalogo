package com.saveurs.maison.catalog.controller;

import com.saveurs.maison.catalog.dto.WineForAiDto;
import com.saveurs.maison.catalog.dto.CheeseForAiDto;
import com.saveurs.maison.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/ai")
@CrossOrigin(origins = "*") // restringir luego
public class AiCatalogController {

    private final CatalogService catalogService;

    public AiCatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/wines")
    public ResponseEntity<List<WineForAiDto>> getWinesForAi(
            @RequestParam(name = "inStock", defaultValue = "true") boolean inStock
    ) {
        List<WineForAiDto> wines = catalogService.getWinesForAi(inStock);
        return ResponseEntity.ok(wines);
    }

    @GetMapping("/cheeses")
    public ResponseEntity<List<CheeseForAiDto>> getCheesesForAi(
            @RequestParam(name = "inStock", defaultValue = "true") boolean inStock
    ) {
        List<CheeseForAiDto> cheeses = catalogService.getCheesesForAi(inStock);
        return ResponseEntity.ok(cheeses);
    }
}
