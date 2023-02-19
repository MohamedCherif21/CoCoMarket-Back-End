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
public class Categorie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private  Integer id;
    private String type;
    @OneToMany(mappedBy = "Categories")
    private Set<Produit> Produit_categories;
}
