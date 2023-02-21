package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Commande_Repository extends JpaRepository<Commande, Integer> {

    @Query("SELECT c FROM Commande c where (c.id =:idcommand) and (c.Affected=false) ")
    public Commande getnotaffectedCommand(@Param("idcommand") Integer idcommande);

}
