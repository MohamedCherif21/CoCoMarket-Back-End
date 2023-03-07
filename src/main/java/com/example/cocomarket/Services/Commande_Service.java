package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Interfaces.ICommande;
import com.example.cocomarket.Repository.Commande_Repository;
import com.example.cocomarket.Repository.Livraison_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class Commande_Service implements ICommande {
    @Autowired
    Commande_Repository cr;
    @Autowired
    Livraison_Repository lr;
    @Override
    public Livraison affectercamandtolivaison(String Region,Livraison l) {
        List<Commande> c=cr.getnotaffectedCommand(Region);
        LocalDate d;
        float x=0,y=0;
        for (Commande i:c)
        {
            x+=i.getSommeVolume();
            y+=i.getSommeWeight();
            i.setAffected(true);
          i.setLivraison_commande(l);
        }
        l.setWeightL(y);
       l.setVolumeL(x);
       l.setRegion(Region);
        d=LocalDate.now();
        l.setDate_Sortie(d);
        lr.save(l);
        return l;
        }



    }

