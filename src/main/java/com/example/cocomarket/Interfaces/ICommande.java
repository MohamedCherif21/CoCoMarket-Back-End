package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Livraison;

public interface ICommande {
    public Livraison affectercamandtolivaison(Integer id_c,Livraison l);
}
