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
public class Shop {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String adresse;
    private String nom;
    private String img;
    private String description;
    private Integer IBAN;
    private Integer BIC;
    @Enumerated(EnumType.STRING)
    private Status traitement;
    @ManyToOne(cascade = CascadeType.ALL)
    private User userShop;
    @OneToMany(mappedBy = "Shopes")
    private Set<Produit> Produit_shopes;
    @ManyToOne(cascade = CascadeType.ALL)
    private Contrat Contrat_shop;

}
