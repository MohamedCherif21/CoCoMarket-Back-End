package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Interfaces.ICommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Commande_Controller {
    @Autowired
    ICommande ic;
    @PostMapping("/add-assign-liv/{idC}")
    @ResponseBody
    public Livraison addLivwithcommand(@RequestBody Livraison l,
                                                      @PathVariable("idC") Integer idC)
    {
        Livraison liv = ic.affectercamandtolivaison(idC,l);
        return liv;
    }
}
