package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Interfaces.ICommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commande")
public class Commande_Controller {
    @Autowired
    ICommande ic;
    @PutMapping("/add-assign-liv/{region}")
    @ResponseBody
    public Livraison addLivwithcommand(@RequestBody Livraison l,
                                                      @PathVariable("region") String region)
    {
        Livraison liv = ic.affectercamandtolivaison(region,l);
        return liv;
    }
}
