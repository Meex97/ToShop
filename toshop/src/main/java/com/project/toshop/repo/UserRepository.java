package com.project.toshop.repo;

import com.project.toshop.model.User;
import org.springframework.data.repository.CrudRepository;

/*
Next we'll create a JPA repository for persisting the User entity.   Spring Data gives us a lot of functionality out-of-the-box here.

We create an interface that extends CrudRepository and supply our User type.  Spring Data will use the information supplied to route requests
to the appropriate repository implementation on our behalf.
 */





public interface UserRepository extends CrudRepository<User, Long > {

    User findByUsername(String username);
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
}


