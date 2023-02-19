package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Produit__Repository extends JpaRepository<Produit, Integer> {
}
