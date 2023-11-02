package com.example.socialmedialowai.service;


import com.example.socialmedialowai.model.UserEntity;
import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    UserEntity getUserByUsername(String username);
    UserEntity getUserByEmail(String email);
    UserEntity createUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    void deleteUser(Long id);
}
