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


    @GetMapping("/users/getclienti")
    public List<User> getClienti(){

       List<User> clienti = new ArrayList<>();
        for(User c : repository.findAll()){
            if (c.getType()==1){
                clienti.add(c);
           }
        }

        return clienti;
    }



    //Creazioni
    @PostMapping(value = "/users/createUser")
    public User postCreateUser(@RequestBody User user){
        User _user = repository.save(new User(user.getName(), user.getSurname(),user.getUsername(),user.getPassword(),user.getAddress(),user.getPhone(),user.getType()));
        return _user;
    }

    @PostMapping(value = "/users/createFornitore")
    public Fornitore postCreateFornitore(@RequestBody Fornitore fornitore){
        Fornitore _fornitore = repository.save(new Fornitore(fornitore.getName(), fornitore.getSurname(),fornitore.getUsername(),fornitore.getPassword(),fornitore.getAddress(),fornitore.getPhone(),fornitore.getType(),fornitore.getNegozio(),fornitore.getP_IVA()));
        return _fornitore;
    }

    @PostMapping(value = "/users/createCliente")
    public Cliente postCreateCliente(@RequestBody Cliente cliente){
        Cliente _cliente = repository.save(new Cliente(cliente.getName(), cliente.getSurname(),cliente.getUsername(),cliente.getPassword(),cliente.getAddress(),cliente.getPhone(), cliente.getType(),cliente.getStudente(),cliente.getPremium(),cliente.getCrediti()));

        return _cliente;
    }

    @PostMapping(value = "/users/createAdmin")
    public Admin postCreateAdmin(@RequestBody Admin admin){
        Admin _admin = repository.save(new Admin(admin.getName(), admin.getSurname(), admin.getUsername(),admin.getPassword(),admin.getAddress(),admin.getPhone(), admin.getType(),admin.getMatricola(),admin.getReparto()));

        return _admin;
    }



/*
    //Registration

    @GetMapping("/users/registration")
   // @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }



    @PostMapping(value = "/users/registration")
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid UserDto accountDto,
             BindingResult result, WebRequest request, Errors errors) {
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        // rest of the implementation
        try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (final Exception ex) {
            LOGGER.warn("Unable to register user", ex);
            return new ModelAndView("emailError", "user", UserDto);
        }
        return new ModelAndView("successRegister", "user", userDto);
    }

    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

*/



/*



    @PostMapping(value = "/users/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            return new ModelAndView("successRegister", "user", accountDto);
        }
    }
    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }


 */

}
