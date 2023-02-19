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
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String last_name;
    private String first_name;
    private Boolean premium;//par default 0
    private String email;
    private String password;
    private Float loyalty_point;
    private String Assosiation_info;
    private String FilesImg;
    private String img;
    @OneToMany(mappedBy = "userQuestions" ,cascade = CascadeType.ALL)
    private Set<Question> Questions;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Response> Responses;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<MSG> MSGs;
    @OneToMany(mappedBy = "userShop")
    private Set<Shop> Shops;
    @OneToMany(mappedBy = "userKoffa")
    private Set<Koffa> Koffas;
    @OneToOne(cascade = CascadeType.ALL)
    private CART cart;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Raiting_Product> raiting_products;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Livraison> Livraisons;







}
