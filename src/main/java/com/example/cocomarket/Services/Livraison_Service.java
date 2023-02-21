package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Interfaces.ILivraison;
import com.example.cocomarket.Repository.Livraison_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Livraison_Service implements ILivraison {
@Autowired
    Livraison_Repository lr;

    @Override
    public Livraison addLivraison(Livraison l) {
return lr.save(l);   }

    @Override
    public Livraison updateLivraison(Livraison l) {
     return lr.save(l);
    }

    @Override
    public Livraison findbyidLivraison(Integer idliv) {
    return lr.findById(idliv).orElse(null);
    }

    @Override
    public void deleteLiv(Integer idLiv) {
     lr.deleteById(idLiv);
    }

    @Override
    public List<Livraison> retrieveAllLiv() {
        return lr.findAll();
    }
}
