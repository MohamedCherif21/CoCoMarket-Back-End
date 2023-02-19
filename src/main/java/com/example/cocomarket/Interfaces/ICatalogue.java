package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Catalogue;

import java.util.List;
import java.util.Optional;

public interface ICatalogue {
    List<Catalogue> findAll();

    Optional<Catalogue> findById(Integer id);

    Catalogue save(Catalogue catalogue);

    void deleteById(Integer id);
}
