package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Livraison;

import java.util.List;

public interface ICommande {
    public Livraison affectercamandtolivaison(String Region,Livraison l);



    Commande Confirmer_Commande(Commande commande, Integer idCart);

    List<Commande> rechercher(String parametre);

    void Annuler_Commande(Integer idCommande) ;

    List<Commande> Afficher_AllCommandes();

    Commande Afficher_Commandes(Integer idCommande);

}
