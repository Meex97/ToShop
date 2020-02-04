package com.project.toshop.repo;

import com.project.toshop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long > {

    User findByUsername(String username);
}


