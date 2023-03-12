package com.example.cocomarket.Entity;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private LocalDate dateCmd;
    private String shop_name;
    private String buyer_name;
    private Float tax;
    private Float totale;
    private String description;
    private Boolean archive;
    private Boolean Affected;//1 bch twali livraison
    private String regionClient;
    private Float SommeWeight ;
    private Float SommeVolume;
@OneToOne
    private CART Commande_cart;
@ManyToOne
    private Livraison Livraison_commande;


}
