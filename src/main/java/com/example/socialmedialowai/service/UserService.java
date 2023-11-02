package com.example.socialmedialowai.service;


import com.example.socialmedialowai.dto.request.UserCreationDTO;
import com.example.socialmedialowai.dto.response.UserDTO;
import com.example.socialmedialowai.model.UserE;
import java.util.List;

public interface UserService {
    List<UserE> getAllUsers();
    UserE getUserById(Long id);
    UserE getUserByUsername(String username);
    UserE getUserByEmail(String email);
    UserE createUser(UserCreationDTO dto);
    UserE updateUser(Long userId, UserDTO dto);
    void deleteUser(Long id);

    void followUser(Long userId, Long followeeId);

    void unfollowUser(Long userId, Long followeeId);

}
