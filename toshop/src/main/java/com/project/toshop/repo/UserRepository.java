package com.project.toshop.repo;

import com.project.toshop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
Next we'll create a JPA repository for persisting the User entity.   Spring Data gives us a lot of functionality out-of-the-box here.

We create an interface that extends CrudRepository and supply our User type.  Spring Data will use the information supplied to route requests
to the appropriate repository implementation on our behalf.
 */




@Repository
public interface UserRepository extends CrudRepository<User, Long > {

    User findByUserName(String userName);
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
}


