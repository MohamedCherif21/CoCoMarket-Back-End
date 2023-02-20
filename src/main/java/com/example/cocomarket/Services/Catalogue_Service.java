package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Catalogue;
import com.example.cocomarket.Interfaces.ICatalogue;
import com.example.cocomarket.Repository.Catalogue_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Catalogue_Service implements ICatalogue {
    @Autowired
    Catalogue_Repository catalogueRepository;

    @Override
    public List<Catalogue> findAll() {
        return catalogueRepository.findAll();
    }

    @Override
    public Optional<Catalogue> findById(Integer id) {
        return catalogueRepository.findById(id);
    }

    @Override
    public Catalogue save(Catalogue catalogue) {
        return catalogueRepository.save(catalogue);
    }

    @Override
    public void deleteById(Integer id) {
        catalogueRepository.deleteById(id);
    }
}


