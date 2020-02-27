package com.project.toshop.utils;

import com.project.toshop.model.User;
import com.project.toshop.model.UserDto;
import com.project.toshop.validation.EmailExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}
