package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.UserRequest;
import com.example.socialmedialowai.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRequest user) {
        return null;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return null;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return null; /*userRepository.findById(id).orElse(null); */
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
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
