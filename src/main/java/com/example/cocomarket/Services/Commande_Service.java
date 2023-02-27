package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Interfaces.ICommande;
import com.example.cocomarket.Repository.Commande_Repository;
import com.example.cocomarket.Repository.Livraison_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class Commande_Service implements ICommande {
    @Autowired
    Commande_Repository cr;
    @Autowired
    Livraison_Repository lr;
    @Override
    public Livraison affectercamandtolivaison(Integer id_c,Livraison l) {
        Commande c=cr.getnotaffectedCommand(id_c);
       //c.setLivraison_commande(l);
        return lr.save(l);

        }



    }

