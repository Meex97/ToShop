package com.project.toshop.controller;

import com.project.toshop.model.Admin;
import com.project.toshop.model.Cliente;
import com.project.toshop.model.Fornitore;
import com.project.toshop.model.User;
import com.project.toshop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/users")
    public List<User> getAllUsers(){

        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);

        return users;
    }

    //Creazioni
    @PostMapping(value = "/users/createUser")
    public User postCreateUser(@RequestBody User user){
        User _user = repository.save(new User(user.getName(), user.getSurname(),user.getUsername(),user.getPassword(),user.getAddress(),user.getPhone()));
        return _user;
    }

    @PostMapping(value = "/users/createFornitore")
    public Fornitore postCreateFornitore(@RequestBody Fornitore fornitore){
        Fornitore _fornitore = repository.save(new Fornitore(fornitore.getName(), fornitore.getSurname(),fornitore.getUsername(),fornitore.getPassword(),fornitore.getAddress(),fornitore.getPhone(),fornitore.getNegozio(),fornitore.getP_IVA()));
        return _fornitore;
    }

    @PostMapping(value = "/users/createCliente")
    public Cliente postCreateCliente(@RequestBody Cliente cliente){
        Cliente _cliente = repository.save(new Cliente(cliente.getName(), cliente.getSurname(),cliente.getUsername(),cliente.getPassword(),cliente.getAddress(),cliente.getPhone(),cliente.getStudente(),cliente.getPremium(),cliente.getCrediti()));

        return _cliente;
    }

    @PostMapping(value = "/users/createAdmin")
    public Admin postCreateAdmin(@RequestBody Admin admin){
        Admin _admin = repository.save(new Admin(admin.getName(), admin.getSurname(), admin.getUsername(),admin.getPassword(),admin.getAddress(),admin.getPhone(),admin.getMatricola(),admin.getReparto()));

        return _admin;
    }


}
