package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Koffa;
import com.example.cocomarket.Interfaces.IKoffa;
import com.example.cocomarket.Repository.Koffa_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Koffa_Service implements IKoffa {

    @Autowired
    private Koffa_Repository koffaRepository;

    @Override
    public Koffa saveKoffa(Koffa koffa) {
        return koffaRepository.save(koffa);
    }

    @Override
    public Koffa updateKoffa(Koffa koffa) {
        return koffaRepository.save(koffa);
    }

    @Override
    public void deleteKoffaById(Integer id) {
        koffaRepository.deleteById(id);
    }

    @Override
    public List<Koffa> getAllKoffas() {
        return koffaRepository.findAll();
    }

    @Override
    public Koffa getKoffaById(Integer id) {
        return koffaRepository.findById(id).orElse(null);
    }
}
