package com.demadev.service;

import com.demadev.exception.UserException;
import com.demadev.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUser();
    User updateUser(User user, Long id) throws UserException;
    void deleteUserById(Long id) throws UserException;

}
