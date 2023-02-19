package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Koffa;
import com.example.cocomarket.Services.Koffa_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/koffas")
public class KoffaController {
    @Autowired
    private Koffa_Service koffaService;

    @GetMapping
    public ResponseEntity<List<Koffa>> getAllKoffas() {
        List<Koffa> koffas = koffaService.getAllKoffas();
        return new ResponseEntity<>(koffas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Koffa> getKoffaById(@PathVariable("id") Integer id) {
        Koffa koffa = koffaService.getKoffaById(id);
        return new ResponseEntity<>(koffa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Koffa> saveKoffa(@RequestBody Koffa koffa) {
        Koffa savedKoffa = koffaService.saveKoffa(koffa);
        return new ResponseEntity<>(savedKoffa, HttpStatus.CREATED);
    }

}
