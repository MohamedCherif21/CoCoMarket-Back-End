package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Catalogue;
import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Repository.Catalogue_Repository;
import com.example.cocomarket.Repository.Produit__Repository;
import com.example.cocomarket.Services.Catalogue_Service;
import com.example.cocomarket.Services.Produit__Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@RestController
@RequestMapping("/api/catalogue")
public class CocoController {

    @Autowired
    private Catalogue_Service catalogueService;

    private final Produit__Service produitService;
    private final Produit__Repository produit__Repository;
    private final Catalogue_Repository catalogue_Repository;

    public CocoController(Produit__Service produitService,
                          Produit__Repository produit__Repository,
                          Catalogue_Repository catalogue_Repository) {
        this.produitService = produitService;
        this.produit__Repository = produit__Repository;
        this.catalogue_Repository = catalogue_Repository;
    }

    @GetMapping("")
    public List<Catalogue> getAllCatalogues() {
        return catalogueService.getAllCatalogues();
    }

    @GetMapping("/{id}")
    public Optional<Catalogue> getCatalogueById(@PathVariable Integer id) {
        return catalogueService.getCatalogueById(id);
    }

    @PostMapping("")
    public Catalogue addCatalogue(@RequestBody Catalogue catalogue) {
        return catalogueService.addCatalogue(catalogue);
    }

    @PutMapping("")
    public Catalogue updateCatalogue(@RequestBody Catalogue catalogue) {
        return catalogueService.updateCatalogue(catalogue);
    }

    @DeleteMapping("/{id}")
    public void deleteCatalogueById(@PathVariable Integer id) {
        catalogueService.deleteCatalogueById(id);
    }

    @DeleteMapping("")
    public void deleteAllCatalogues() {
        catalogueService.deleteAllCatalogues();
    }



    @PostMapping("/{catalogueId}/produits/{produitId}")
    public void addProduitToCatalogue(@PathVariable Integer catalogueId, @PathVariable Integer produitId) {
        catalogueService.addProduitToCatalogue(catalogueId, produitId);
    }

    @DeleteMapping("/{catalogueId}/produits/{produitId}")
    public void removeProduitFromCatalogue(@PathVariable Integer catalogueId, @PathVariable Integer produitId) {
        catalogueService.removeProduitFromCatalogue(catalogueId, produitId);
    }


    @PostMapping("/top50catalogue")
    public ResponseEntity<Catalogue> createTop50Catalogue() {
        Catalogue catalogue = catalogueService.createTop50Catalogue();
        return ResponseEntity.ok(catalogue);
    }


    @PostMapping("/top-rated-products")
    public ResponseEntity<String> createTopRatedProductsCatalogue() {
        catalogueService.createTopRatedProductsCatalogue();
        return ResponseEntity.status(HttpStatus.CREATED).body("Catalogue créé avec succès.");
    }









}
