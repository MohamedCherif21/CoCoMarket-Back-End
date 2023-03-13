package com.example.cocomarket.Repository;


import com.example.cocomarket.Entity.Produit_Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Produit_Cart_repository extends JpaRepository<Produit_Cart, Integer> {
}
