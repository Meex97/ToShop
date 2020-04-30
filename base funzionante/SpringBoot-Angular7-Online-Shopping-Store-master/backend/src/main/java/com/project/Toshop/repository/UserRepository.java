package com.project.Toshop.repository;


import com.project.Toshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;



public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    //find by id

    User findById(Long id);
    Collection<User> findAllByRole(String role);

}
