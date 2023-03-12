package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Catalogue;
import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Entity.Raiting_Product;
import com.example.cocomarket.Interfaces.ICatalogue;
import com.example.cocomarket.Repository.Catalogue_Repository;
import com.example.cocomarket.Repository.Produit__Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class Catalogue_Service implements ICatalogue {

    @Autowired
    private Catalogue_Repository catalogueRepository;
    @Autowired
    private Produit__Repository produitRepository;





    public List<Catalogue> getAllCatalogues() {
        return catalogueRepository.findAll();
    }

    public Optional<Catalogue> getCatalogueById(Integer id) {
        return catalogueRepository.findById(id);
    }

    public Catalogue addCatalogue(Catalogue catalogue) {
        return catalogueRepository.save(catalogue);
    }

    public Catalogue updateCatalogue(Catalogue catalogue) {
        return catalogueRepository.save(catalogue);
    }

    public void deleteCatalogueById(Integer id) {
        catalogueRepository.deleteById(id);
    }

    public void deleteAllCatalogues() {
        catalogueRepository.deleteAll();
    }

    public void addProduitToCatalogue(Integer catalogueId, Integer produitId) {
        Optional<Catalogue> optionalCatalogue = catalogueRepository.findById(catalogueId);
        Optional<Produit> optionalProduit = produitRepository.findById(produitId);
        if (optionalCatalogue.isPresent() && optionalProduit.isPresent()) {
            Catalogue catalogue = optionalCatalogue.get();
            Produit produit = optionalProduit.get();
            catalogue.getProduits().add(produit);
            produit.getCatalogues().add(catalogue);
            catalogueRepository.save(catalogue);
            produitRepository.save(produit);
        }
    }

    public void removeProduitFromCatalogue(Integer catalogueId, Integer produitId) {
        Optional<Catalogue> optionalCatalogue = catalogueRepository.findById(catalogueId);
        Optional<Produit> optionalProduit = produitRepository.findById(produitId);
        if (optionalCatalogue.isPresent() && optionalProduit.isPresent()) {
            Catalogue catalogue = optionalCatalogue.get();
            Produit produit = optionalProduit.get();
            catalogue.getProduits().remove(produit);
            produit.getCatalogues().remove(catalogue);
            catalogueRepository.save(catalogue);
            produitRepository.save(produit);
        }
    }


    public Catalogue createTop50Catalogue() {
        List<Produit> top50Produits = produitRepository.findTop50ByOrderByQuantiteVendueDesc();
        Catalogue catalogue = new Catalogue();
        catalogue.setNom("Top 50 Produits");
        catalogue.setDescription("Les 50 produits les plus vendus");

        Set<Produit> produits = new HashSet<>(top50Produits);
        catalogue.setProduits(produits);

        return catalogueRepository.save(catalogue);
    }

    public void createTopRatedProductsCatalogue() {
        // Récupérer les 5 produits les mieux notés
        List<Produit> topRatedProducts = produitRepository.findAll()
                .stream()
                .filter(p -> p.getRaiting_prod() != null && !p.getRaiting_prod().isEmpty())
                .sorted(Comparator.comparing(p -> -getAverageScore(p)))
                .limit(3)
                .collect(Collectors.toList());

        // Créer le catalogue contenant les 5 produits les mieux notés
        Catalogue topRatedProductsCatalogue = new Catalogue();
        topRatedProductsCatalogue.setNom("Top Rated Products");
        topRatedProductsCatalogue.setDescription("Catalogue contenant les 5 produits les plus notés.");
        topRatedProductsCatalogue.setImg("https://example.com/top-rated-products.png");
        topRatedProductsCatalogue.setProduits(new HashSet<>(topRatedProducts));

        // Enregistrer le catalogue dans la base de données
        catalogueRepository.save(topRatedProductsCatalogue);
    }

    private Double getAverageScore(Produit produit) {
        return produit.getRaiting_prod().stream()
                .mapToInt(r -> r.getScore())
                .average()
                .orElse(0.0);
    }



}


