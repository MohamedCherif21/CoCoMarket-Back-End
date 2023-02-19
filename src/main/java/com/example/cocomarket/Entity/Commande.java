package com.example.cocomarket.Entity;

import java.time.LocalDate;
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
@OneToOne
    private CART Commande_cart;
@ManyToOne(cascade = CascadeType.ALL)
    private Livraison Livraison_commande;


}
