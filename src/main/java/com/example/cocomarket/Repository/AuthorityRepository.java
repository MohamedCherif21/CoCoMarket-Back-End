package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Autority;
import com.example.cocomarket.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AuthorityRepository extends JpaRepository<Autority, Integer> {

    @Query(value =
            "select count (t) from Autority t "+
                    "where t.name = :Role "
            //  "group by t.UserAuth "
    )
    List<User> findAllUserByRole(String Role);


}
