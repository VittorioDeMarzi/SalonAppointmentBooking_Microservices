package com.demadev.controller;

import com.demadev.model.User;
import com.demadev.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        throw new Exception("User not found");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,
                           @PathVariable("userId") Long id) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            User existingUser = opt.get();
            existingUser.setFullName(user.getFullName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setRole(user.getRole());
            existingUser.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(userRepository.save(existingUser), HttpStatus.OK);
        }
        throw new Exception("User not found with id" + id);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") Long id) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()) {
            throw new Exception("User not found with id" + id);
        }
        userRepository.delete(opt.get());
        return ResponseEntity.noContent().build();
    }
}
