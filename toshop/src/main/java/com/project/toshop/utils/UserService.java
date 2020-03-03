package com.project.toshop.utils;

import com.project.toshop.model.Role;
import com.project.toshop.model.User;
import com.project.toshop.repo.RoleRepository;
import com.project.toshop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;

/*

The service layer is the middle layer between the presentation (controllers) and
data store (repository). It abstracts business logic and data access.

UserService.java
The UserService performs operations against the UserRepository. We need the ability to find a
user by e-mail address or confirmation token, and also save a user.
 */


//@Service
//public class UserService implements IUserService {
@Service
public class UserService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    /* 2
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //private UserRepository repository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findByConfirmationToken(String confirmationToken){
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }


*/


    /* 1
    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException {

        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address:"  + accountDto.getEmail());
        }
        User user = new User();
        user.setName(accountDto.getFirstName());
        user.setSurname(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setType(1);
        //user.setType(Arrays.asList("ROLE_USER"));
        return repository.save(user);
    }
    private boolean emailExists(String email) {
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }


     */
}
