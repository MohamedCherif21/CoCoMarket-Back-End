package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.CART;
import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Etat;
import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Interfaces.ICommande;
import com.example.cocomarket.Repository.Cart_Repository;
import com.example.cocomarket.Repository.Commande_Repository;
import com.example.cocomarket.Repository.Livraison_Repository;
import com.example.cocomarket.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Commande_Service implements ICommande {

    @Autowired
    Commande_Repository cr;
    @Autowired
    Livraison_Repository lr;
    @Autowired
    User_Repository ur;
    @Autowired
    Cart_Repository car;



    @Override
    public Livraison affectercamandtolivaison(String Region,Livraison l) {
        List<Commande> c=cr.getnotaffectedCommand(Region);
        LocalDate d;
        float x=0,y=0;
        for (Commande i:c)
        {
            x+=i.getSommeVolume();
            y+=i.getTotal_weight();
            i.setEtat(Etat.VALIDATED);
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

    @Override
    public Commande Confirmer_Commande(Commande commande, Integer idCart)  {
        CART cart = car.findById(idCart).orElse(null);
        commande.setCommande_cart(cart);
        commande.setTotal_price(cart.getTotal_price());
        commande.setTotal_weight(cart.getTotal_weight());
        commande.setBuyer_email(cart.getUser().getEmail());
        commande.setShop_address(String.valueOf(cart.getUser().getShops()));
        commande.setNbProd(cart.getNbProd());
        commande.setTax(cart.getTotal_price() + 18 % -cart.getTotal_price());
        commande.setEtat(Etat.EDITABLE);

        // commande.setShop_name(String.valueOf(cart.getUser()));
        return cr.save(commande);
    }

    @Scheduled(fixedDelay = 180000) // 3 minutes en millisecondes
    public void changeOrderStatus() {

        List<Commande> commandes = cr.findAll();
        for(Commande commande :commandes) {
            if (commande.getEtat() == Etat.EDITABLE) {
                System.out.println("change");
                commande.setEtat(Etat.WAITING);

            }
            cr.save(commande);
        }

    }

    @Override
    public List<Commande> Afficher_AllCommandes() {
        return cr.findAll();

    }

    @Override
    public Commande Afficher_Commandes(Integer idCommande) {
        return cr.findById(idCommande).get();
    }

    @Override
    public List<Commande> rechercher(String parametre) {
        List<Commande> commandes = cr.findAll();
        return commandes.stream()
                .filter(c -> c.getShop_name().contains(parametre) || c.getDescription().contains(parametre)|| c.getShop_address().contains(parametre)|| c.getBuyer_address().contains(parametre))
                .collect(Collectors.toList());
    }

    @Override
    public void Annuler_Commande(Integer idCommande) {
        Commande commande = cr.findById(idCommande).orElse(null);
        LocalDateTime currentTimeNow = LocalDateTime.now();
        LocalDateTime Limite = commande.getDateCmd().plusMinutes(60);
        if (currentTimeNow.isBefore(Limite)) {
            cr.delete(commande);
        } else {
            throw new RuntimeException("La commande ne peut plus être annulée.");
        }
    }

}

