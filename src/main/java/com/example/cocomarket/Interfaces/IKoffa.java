package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Koffa;

import java.util.List;

public interface IKoffa {
    Koffa saveKoffa(Koffa koffa);
    Koffa updateKoffa(Koffa koffa);
    void deleteKoffaById(Integer id);
    List<Koffa> getAllKoffas();
    Koffa getKoffaById(Integer id);
}
