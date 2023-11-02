package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.UserRegisterRequest;
import com.example.socialmedialowai.model.UserEntity;
import com.example.socialmedialowai.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@Validated @RequestBody UserRegisterRequest request) {
        UserEntity entity = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserRegisterRequest request) {
        return userService.updateUser(request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{userId}/follow")
    public void followUser(@PathVariable Long userId, @RequestBody Long followeeId) {
        userService.followUser(userId, followeeId);
    }

    @DeleteMapping("/{userId}/follow/{followeeId}")
    public void unfollowUser(@PathVariable Long userId, @PathVariable Long followeeId) {
        userService.unfollowUser(userId, followeeId);
    }
}
