package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.request.UserCreationDTO;
import com.example.socialmedialowai.dto.response.UserDTO;
import com.example.socialmedialowai.model.UserE;
import com.example.socialmedialowai.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.socialmedialowai.mapper.UserMapper.map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreationDTO dto) {
        UserE entity = userService.createUser(dto);
        UserDTO responseDTO = map(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return map(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return map(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable(name = "id") Long userId, @RequestBody UserDTO dto) {
        return map(userService.updateUser(userId, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{userId}/follow")
    public void followUser(@PathVariable Long userId, @RequestParam Long followeeId) {
        userService.followUser(userId, followeeId);
    }

    @DeleteMapping("/{userId}/follow")
    public void unfollowUser(@PathVariable Long userId, @RequestParam Long followeeId) {
        userService.unfollowUser(userId, followeeId);
    }
}
