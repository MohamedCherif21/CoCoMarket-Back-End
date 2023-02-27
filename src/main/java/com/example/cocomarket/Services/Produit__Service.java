package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Entity.Shop;
import com.example.cocomarket.Entity.Status;
import com.example.cocomarket.Interfaces.IProduit;
import com.example.cocomarket.Repository.Produit__Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Produit__Service implements IProduit {
    @Autowired
    private Produit__Repository produitRepository;

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

       public Optional<Produit> getProduitById(Integer id) {
            return Optional.ofNullable(produitRepository.findById(id).orElse(null));
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public void deleteProduitById(Integer id) {
        produitRepository.deleteById(id);
    }







}
