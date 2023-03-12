package com.example.cocomarket.Services;

import com.example.cocomarket.Controller.User_Controller;
import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Entity.User;
import com.example.cocomarket.Entity.Vehicule;
import com.example.cocomarket.Interfaces.IUser;
import com.example.cocomarket.Repository.Livraison_Repository;
import com.example.cocomarket.Repository.Raiting_DelevryMan_Repository;
import com.example.cocomarket.Repository.User_Repository;
import com.example.cocomarket.Repository.Vehicule_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class User_Service implements IUser {
    @Autowired
    User_Repository ur;
    @Autowired
    Vehicule_Repository vr;


    @Override
    public void assignusertoCar(Integer LId, Integer UId) {
        User u=ur.findById(UId).orElse(null);
        Vehicule v=vr.findById(LId).orElse(null);
        u.setCar(v);
        ur.save(u);

    }

    @Override
    public Vehicule assignusertoCarCreate(Integer UId, Vehicule v) {
        User u=ur.findById(UId).orElse(null);
        u.setCar(v);
        ur.save(u);
     return v;
    }

}
