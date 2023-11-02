package com.example.socialmedialowai.service;


import com.example.socialmedialowai.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
