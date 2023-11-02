package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.UserRequest;
import com.example.socialmedialowai.model.UserEntity;
import com.example.socialmedialowai.service.UserService;
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
    public UserEntity registerUser(@RequestBody UserRequest user) {
        return null;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return null;
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return null; /*userRepository.findById(id).orElse(null); */
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
//        userRepository.deleteById(id);
    }

    @PostMapping("/{userId}/follow")
    public void followUser(@PathVariable Long userId, @RequestBody Long followeeId) {
//        User user = userRepository.findById(userId).orElse(null);
//        User followee = userRepository.findById(followeeId).orElse(null);
//
//        if (user != null && followee != null) {
//            user.getFollowing().add(followee);
//            userRepository.save(user);
//        }
    }

    @DeleteMapping("/{userId}/follow/{followeeId}")
    public void unfollowUser(@PathVariable Long userId, @PathVariable Long followeeId) {
//        User user = userRepository.findById(userId).orElse(null);
//        User followee = userRepository.findById(followeeId).orElse(null);
//
//        if (user != null && followee != null) {
//            user.getFollowing().remove(followee);
//            userRepository.save(user);
//        }
    }
}
