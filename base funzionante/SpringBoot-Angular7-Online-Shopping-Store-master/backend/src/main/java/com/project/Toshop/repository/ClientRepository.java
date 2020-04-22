package com.project.Toshop.repository;

import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ClientRepository extends JpaRepository<Client, String> {

    /*@Query("SELECT * FROM Users u WHERE u.idUte = 1")
    Collection<User> findAllActiveUsers();*/
    Client findById(Long id);
    Client findByEmail(String email);
}
