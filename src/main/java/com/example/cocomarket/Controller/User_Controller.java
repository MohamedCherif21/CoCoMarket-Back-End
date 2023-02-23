package com.example.cocomarket.Controller;

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

    @PutMapping(value="/affecter-Livraison-user/{LId}/{UId}")
    public void affectertUserToliv(@PathVariable("LId") Integer LId,
                                                 @PathVariable("UId")Integer UId){

        iu.assignusertodelivery(LId,UId);
    }

}
