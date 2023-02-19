package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Response_Repository extends JpaRepository<Response, Integer> {
}
