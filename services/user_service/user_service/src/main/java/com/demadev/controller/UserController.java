package com.demadev.controller;

import com.demadev.exception.UserException;
import com.demadev.model.User;
import com.demadev.repository.UserRepository;
import com.demadev.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) throws UserException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,
                           @PathVariable("userId") Long id) throws Exception {
        User updatedUser = userService.updateUser(user, id);
        return ResponseEntity.ok(updatedUser);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") Long id) throws Exception {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
    }
}
