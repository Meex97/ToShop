package com.project.Toshop.service.impl;


import com.project.Toshop.entity.Cart;
import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.Supplier;
import com.project.Toshop.entity.User;
import com.project.Toshop.enums.ResultEnum;
import com.project.Toshop.exception.MyException;
import com.project.Toshop.repository.CartRepository;
import com.project.Toshop.repository.ClientRepository;
import com.project.Toshop.repository.SupplierRepository;
import com.project.Toshop.repository.UserRepository;
import com.project.Toshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;


@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SupplierRepository supplierRepository;


    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Client findOneClient(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Supplier findOneSupplier(String email) {
        return supplierRepository.findByEmail(email);
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public User save(User user) {
        //register
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);

            // initial Cart
            Cart savedCart = cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(savedUser);

        } catch (Exception e) {
            throw new MyException(ResultEnum.VALID_ERROR);
        }

    }

    @Override
    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
       // oldUser.setAddress(user.getAddress());
        return userRepository.save(oldUser);
    }

    @Override
    public Client updateClient(Client client) {
        Client oldClient = clientRepository.findByEmail(client.getEmail());
        oldClient.setPassword(passwordEncoder.encode(client.getPassword()));
        oldClient.setName(client.getName());
        oldClient.setPhone(client.getPhone());
        oldClient.setAddress(client.getAddress());
        return clientRepository.save(oldClient);
    }

    @Override
    public Client updateCredits(BigDecimal productPrice, Long id) {
        Client oldClient = clientRepository.findById(id);
        oldClient.setCredits(productPrice.intValue());
        return clientRepository.save(oldClient);
    }


    @Override
    public Supplier updateSupplier(Supplier supplier) {
        Supplier oldSupplier = supplierRepository.findByEmail(supplier.getEmail());
        oldSupplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
        oldSupplier.setName(supplier.getName());
        oldSupplier.setPhone(supplier.getPhone());
        oldSupplier.setAddress(supplier.getAddress());
        return supplierRepository.save(oldSupplier);
    }

}
