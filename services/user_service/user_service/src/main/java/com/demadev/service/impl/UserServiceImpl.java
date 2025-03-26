package com.demadev.service.impl;

import com.demadev.exception.UserException;
import com.demadev.model.User;
import com.demadev.repository.UserRepository;
import com.demadev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        }
        throw new UserException("User not found with id " + id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            User existingUser = opt.get();
            existingUser.setFullName(user.getFullName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setRole(user.getRole());
            existingUser.setUsername(user.getUsername());
            return userRepository.save(existingUser);
        }
        throw new UserException("User not found with id " + id);
    }

    @Override
    public void deleteUserById(Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()) {
            throw new UserException("User not found with id " + id);
        }
        userRepository.delete(opt.get());
    }
}
