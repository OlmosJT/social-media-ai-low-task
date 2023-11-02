package com.example.socialmedialowai.service;


import com.example.socialmedialowai.dto.UserRegisterRequest;
import com.example.socialmedialowai.model.UserEntity;
import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    UserEntity getUserByUsername(String username);
    UserEntity getUserByEmail(String email);
    UserEntity createUser(UserRegisterRequest userRegisterRequest);
    UserEntity updateUser(UserRegisterRequest request);
    void deleteUser(Long id);

    void followUser(Long userId, Long followeeId);

    void unfollowUser(Long userId, Long followeeId);

}
