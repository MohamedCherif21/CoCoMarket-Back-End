package com.example.cocomarket.Entity;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Livraison {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private Boolean validation;
    private LocalDate date_Arrive;
    private LocalDate date_Sortie;
    @Enumerated(EnumType.STRING)
    private Etat_Livraison etat;
    @JsonIgnore
    @ManyToMany(mappedBy = "Livraison_commande")
    private Set<Commande> Command_liv;
    @JsonIgnore
    @OneToOne
    private Raiting_DelevryMan Rating_Liv;

}
