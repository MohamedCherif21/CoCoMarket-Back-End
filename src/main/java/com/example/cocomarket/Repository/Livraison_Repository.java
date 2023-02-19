package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Livraison_Repository extends JpaRepository<Livraison, Integer> {
}
