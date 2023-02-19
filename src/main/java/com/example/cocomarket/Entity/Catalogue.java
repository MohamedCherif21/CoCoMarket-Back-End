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
public class Catalogue {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private  Integer id;
    private String nom;
    private  String description;
    private   String img;
    @ManyToMany(mappedBy = "Catalogues")
    private Set<Produit> Produits_Cats;

}
