package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends JpaRepository<User, Integer> {
}
