package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.CART;
import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Entity.Produit_Cart;
import com.example.cocomarket.Interfaces.ICart;
import com.example.cocomarket.Repository.Cart_Repository;
import com.example.cocomarket.Repository.Produit_Cart_repository;
import com.example.cocomarket.Repository.Produit__Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Set;

@Service
public class Cart_Service implements ICart {

    @Autowired
    Produit__Repository pr;

    @Autowired
    Cart_Repository cr;

    @Autowired
    Produit_Cart_repository pcr;

    /*@Autowired
    private APIContext apiContext ;*/


    @Override
    public CART Add_Product_To_Cart(Integer idProduit, Integer idCart) {

        CART cart = cr.findById(idCart).orElseThrow(() -> new NotFoundException("Cart not found"));
        Produit product = pr.getById(idProduit);
        Produit_Cart cartItem = cart.getItems().stream()
                .filter(item -> item.getProduit().getId().equals(idProduit))
                .findFirst()
                .orElse(null);
        if (cartItem == null) {
            // Add new item to cart
            cartItem = new Produit_Cart(product, 1);
            cart.getItems().add(cartItem);
            cartItem.setCart(cart);
            cartItem.setProduit(product);
            cartItem.setQuantity(1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        cart.setNbProd(cart.getNbProd() + 1);
        cart.setTotal_price(cart.getTotal_price() + product.getPrix());
        cart.setTotal_weight(cart.getTotal_weight() + product.getWeight());
        return cr.save(cart);
    }

    @Override
    public void Remove_Product(Integer idProduit, Integer idCart) {
        Produit produit = pr.getById(idProduit);
        CART cart = cr.findById(idCart).orElse(null);

        Produit_Cart cartItem = cart.getItems().stream()
                .filter(item -> item.getProduit().getId().equals(idProduit))
                .findFirst()
                .orElse(null);
        if (cartItem == null) {
            // remove product from prodcart
            cart.getItems().remove(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            if(cartItem.getQuantity()==0){
                System.out.println("quantity = 0 ");
                pcr.delete(cartItem);

            }
        }

        cart.getItems().remove(produit);
        cart.setNbProd(cart.getNbProd() - 1);
        cart.setTotal_price(cart.getTotal_price() - produit.getPrix());
        cart.setTotal_weight(cart.getTotal_weight() - produit.getWeight());
        cr.save(cart);


    }

    @Override
    public Produit retrive_one_Product(Integer idprodCart, Integer idProduit) {
        Produit_Cart produit_cart = pcr.findById(idprodCart).get();
        Produit product = produit_cart.getProduit();
        if (product.getId().equals(idProduit)) {
            System.out.println("equal"+product);
            return product;
        }
        System.out.println("not equal"+product.getId());
        return null ;
    }
   /* public Produit retrive_one_Product(Integer idProduit) {
        Produit_Cart produit_cart = pcr.findById(idProduit).get();
        Produit product = produit_cart.getProduit();
        return product;
    }*/

    @Override
    public Set<Produit_Cart> Retrive_All_Product_in_cart(Integer idCart) {
        CART cart = cr.findById(idCart).orElse(null);
        assert cart != null;
        return (Set<Produit_Cart>) cart.getItems();
    }

}
