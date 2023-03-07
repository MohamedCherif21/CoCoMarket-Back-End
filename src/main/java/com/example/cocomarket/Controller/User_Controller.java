package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Vehicule;
import com.example.cocomarket.Interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User_Controller {
    @Autowired
    IUser iu;

    @PutMapping(value="/affecter-Car-user/{LId}/{UId}")
    public void affectertUserToCar(@PathVariable("LId") Integer LId,
                                                 @PathVariable("UId")Integer UId){

        iu.assignusertoCar(LId,UId);
    }
    @PutMapping(value="/affecter-Car-user-create/{UId}")
    public Vehicule affectertUserToCarCreate(@RequestBody Vehicule v,
                                   @PathVariable("UId")Integer UId){

        return iu.assignusertoCarCreate(UId,v);
    }
}
