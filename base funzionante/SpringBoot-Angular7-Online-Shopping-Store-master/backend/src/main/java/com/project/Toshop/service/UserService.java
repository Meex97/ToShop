package com.project.Toshop.service;


import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.Supplier;
import com.project.Toshop.entity.User;

import java.math.BigDecimal;
import java.util.Collection;


public interface UserService {
    User findOne(String email);

    Client findOneClient(String email);

    Supplier findOneSupplier(String email);

    Collection<User> findByRole(String role);

    User save(User user);

    User update(User user);

    Client updateClient(Client client);

    Client updateCredits(int productPrice, Long idUtente);

    Supplier updateSupplier(Supplier supplier);

}
