package com.example.cocomarket.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CART {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private  Integer id;
    private Integer nbProd;
    private Float totPrice;
    @OneToMany (cascade = CascadeType.ALL)
    private Set<Produit> Product_Cart;
    @OneToOne (mappedBy = "Commande_cart")
    private Commande ommande_cart;

}
