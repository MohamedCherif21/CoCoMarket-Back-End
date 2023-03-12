package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Comentaire;
import com.example.cocomarket.Interfaces.IComentaire;
import com.example.cocomarket.Repository.Comentaire_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Comentaire_Service implements IComentaire {
    @Autowired
    Comentaire_Repository responseRepository;

    @Override
    public List<Comentaire> getAllResponses() {
        return responseRepository.findAll();
    }

    @Override
    public Optional<Comentaire> getResponseById(Integer id) {
        return responseRepository.findById(id);
    }

    @Override
    public Comentaire addResponse(Comentaire comentaire) {
        return responseRepository.save(comentaire);
    }

    @Override
    public void deleteResponse(Integer id) {
        responseRepository.deleteById(id);
    }
}
