package com.example.cocomarket.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {
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
    private String Files;
    private String img;
    private String region;
    private Boolean enabled;
    private Integer nbr_tentatives;
    private Boolean availability;
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
    @OneToOne(cascade = CascadeType.ALL)
    private Vehicule car;

    @OneToMany(mappedBy = "UserAuth" ,fetch = FetchType.EAGER)

    private Set<Autority> autority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (Autority authority : autority) {
            if (authority !=null)
                authorities.add(new SimpleGrantedAuthority(authority.getName()));
            else
                System.out.println("----- U have no AUtority Bro ----");
        }
        return authorities;
    }
    public Set<Autority> getAuthFromBase(){
        return this.autority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }








}
