package com.example.cocomarket.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String Reference;
    private String nom;
    private String img;
    private String description;
    private Long prix;
    private Float weight;
    private Boolean EtatsProduit;//mawjoud ou non
    @Enumerated(EnumType.STRING)
    private Status status;//ywefe9 3lih lbaye3 bch ybi3ou ou non//par default Null
    private LocalDate datePublication;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Raiting_Product> raiting_prod;


    @ManyToOne(cascade = CascadeType.ALL)
    private Categorie Categories;

    @ManyToOne
    private Shop Shopes;

    @ManyToMany(mappedBy = "produits")
    private Set<Catalogue> catalogues = new HashSet<>();

    private Integer quantiteVendue;

    private Integer pourcentagePromotion;

    // Constructeurs
    public Produit(String Reference, String nom, String img, String description, Long prix, Boolean EtatsProduit,
                   Status status, LocalDate datePublication, Set<Raiting_Product> raiting_prod, Categorie Categories,
                   Shop Shopes, Set<Catalogue> catalogues, Integer quantiteVendue, Integer pourcentagePromotion) {
        this.Reference = Reference;
        this.nom = nom;
        this.img = img;
        this.description = description;
        this.prix = prix;
        this.EtatsProduit = EtatsProduit;
        this.status = status;
        this.datePublication = datePublication;
        this.raiting_prod = raiting_prod;
        this.Categories = Categories;
        this.Shopes = Shopes;
        this.catalogues = catalogues;
        this.quantiteVendue = quantiteVendue;
        this.pourcentagePromotion = pourcentagePromotion;
    }


}
