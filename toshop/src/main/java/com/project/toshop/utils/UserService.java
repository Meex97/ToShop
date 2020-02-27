package com.project.toshop.utils;

import com.project.toshop.model.User;
import com.project.toshop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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



    /*
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
