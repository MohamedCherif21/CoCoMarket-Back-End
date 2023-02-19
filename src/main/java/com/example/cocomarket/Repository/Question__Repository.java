package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Question__Repository extends JpaRepository<Question, Integer> {
}
