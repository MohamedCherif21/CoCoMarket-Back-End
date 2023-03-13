package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Entity.Produit_Cart;
import com.example.cocomarket.Repository.Commande_Repository;
import com.example.cocomarket.Repository.Produit__Repository;
import com.example.cocomarket.Services.Cart_Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Slf4j
//@AllArgsConstructor
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    Cart_Service cart_service;

    @Autowired
    Commande_Repository cr ;

    @Autowired
    Produit__Repository produit__repository ;
/*
    @PostMapping("/panier/ajouter-produit/{idProduit}")
    public void ajouterProduit_aupanier(@PathVariable Integer idProduit, HttpSession session) {
        CART panier = (CART) session.getAttribute("panier"); // récupère le panier de l'utilisateur depuis la session
        if (panier == null) {
            panier = new CART();
            session.setAttribute("panier", panier); // enregistre le panier dans la session s'il n'existe pas encore
        }
        cart_service.Add_Product_To_Cart(idProduit,panier); // appelle la méthode ajouterProduit() du service
    }
    */


    @PostMapping("/panier/ajouter-produit/{idProduit}/{idCart}")
    public void ajouterProduit_aupanier(@PathVariable ("idProduit") Integer idProduit,
                                        @PathVariable ("idCart") Integer idCart) {
        cart_service.Add_Product_To_Cart(idProduit,idCart);
    }

    /* @PostMapping("/panier/ajouter-produit")
    public CART addToCart(@RequestBody Produit product, @RequestBody CART cart) {
        return cart_service.addToCart(cart, product);
    }

/*
    @DeleteMapping("/panier/supprimer-produit/{idProduit}")
    public void supprimerProduit(@PathVariable Integer idProduit, HttpSession session) {
        CART panier = (CART) session.getAttribute("panier");
        if (panier != null) {
            Produit produit = produit__repository.getReferenceById(idProduit);
            cart_service.Remove_Product(panier, produit);
        }*/

    @DeleteMapping("/panier/supprimer-produit/{idProduit}/{idCart}")
    public void supprimerProduit(@PathVariable ("idProduit")Integer idProduit,
                                 @PathVariable ("idCart") Integer idCart) {
            cart_service.Remove_Product(idProduit, idCart);
    }

    @GetMapping("/retrieve-Produit/{Cart-id}/{Produit-id}")
    public Produit retrieveProduit(@PathVariable("Cart-id") Integer idCart,
                                   @PathVariable("Produit-id") Integer idProduit) {
        return cart_service.retrive_one_Product(idCart,idProduit);
    }


    @GetMapping("/retrieve-Product_in_cart/{prodcart-id}")
    public Set<Produit_Cart> retrieveProduct_in_cart(@PathVariable("prodcart-id") Integer IdCart) {
        return cart_service.Retrive_All_Product_in_cart(IdCart);
    }

/**************************PAYPAL**********************************/






}
