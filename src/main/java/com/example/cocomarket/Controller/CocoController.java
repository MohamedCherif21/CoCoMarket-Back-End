package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Catalogue;
import com.example.cocomarket.Interfaces.ICatalogue;
import com.example.cocomarket.Services.Catalogue_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalogue")
public class CocoController {

    @Autowired
    ICatalogue catalogueInterface;

    // get all catalogues
    @GetMapping("")
    public List<Catalogue> getAllCatalogues() {
        return catalogueInterface.findAll();
    }

    // get a specific catalogue by id
    @GetMapping("/{id}")
    public Optional<Catalogue> getCatalogue(@PathVariable Integer id) {
        return catalogueInterface.findById(id);
    }

    // create a new catalogue
    @PostMapping("")
    public Catalogue createCatalogue(@RequestBody Catalogue catalogue) {
        return catalogueInterface.save(catalogue);
    }

    // update a catalogue by id
    @PutMapping("/{id}")
    public Catalogue updateCatalogue(@PathVariable Integer id, @RequestBody Catalogue catalogue) {
        Optional<Catalogue> optionalCatalogue = catalogueInterface.findById(id);
        if (optionalCatalogue.isPresent()) {
            Catalogue existingCatalogue = optionalCatalogue.get();
            existingCatalogue.setNom(catalogue.getNom());
            existingCatalogue.setDescription(catalogue.getDescription());
            existingCatalogue.setImg(catalogue.getImg());
            return catalogueInterface.save(existingCatalogue);
        } else {
            return null;
        }
    }

    // delete a catalogue by id
    @DeleteMapping("/{id}")
    public void deleteCatalogue(@PathVariable Integer id) {
        catalogueInterface.deleteById(id);
    }
}
