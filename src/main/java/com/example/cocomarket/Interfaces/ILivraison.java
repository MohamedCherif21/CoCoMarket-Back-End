package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Livraison;

import java.util.List;

public interface ILivraison {
    public Livraison addLivraison (Livraison l);
    public Livraison updateLivraison (Livraison l);
    public Livraison findbyidLivraison (Integer idliv);
    void deleteUniversite(Integer idLiv);
    public List<Livraison> retrieveAllLiv();

}
