package com.project.Toshop.service;


import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.User;

import java.util.Collection;


public interface UserService {
    User findOne(String email);

    Client findOneClient(String email);

    Collection<User> findByRole(String role);

    User save(User user);

    User update(User user);
}
