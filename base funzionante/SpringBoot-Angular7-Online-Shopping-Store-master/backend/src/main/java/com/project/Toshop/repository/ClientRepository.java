package com.project.Toshop.repository;

import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

    Client findByEmail(String email);
}
