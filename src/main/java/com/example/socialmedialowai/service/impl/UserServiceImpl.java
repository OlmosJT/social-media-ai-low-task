package com.example.socialmedialowai.service.impl;

import com.example.socialmedialowai.dto.request.UserCreationDTO;
import com.example.socialmedialowai.dto.response.UserDTO;
import com.example.socialmedialowai.model.UserE;
import com.example.socialmedialowai.repository.UserRepository;
import com.example.socialmedialowai.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserE> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserE getUserById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserE getUserByUsername(String username) throws EntityNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(EntityExistsException::new);
    }

    @Override
    public UserE getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityExistsException::new);
    }

    @Override
    public UserE createUser(UserCreationDTO dto) throws EntityExistsException {
        if(userRepository.existsByUsernameOrEmail(dto.username(), dto.email())) {
            throw new EntityExistsException("User already exist");
        }

        UserE user = UserE.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .posts(emptyList())
                .likedPosts(emptyList())
                .followers(emptyList())
                .build();

        return userRepository.save(user);
    }

    @Override
    public UserE updateUser(Long userId, UserDTO dto) throws EntityNotFoundException {
        UserE user = userRepository
                .findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void followUser(Long userId, Long followeeId) throws EntityNotFoundException {
        UserE user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        UserE followee = userRepository
                .findById(followeeId)
                .orElseThrow(() -> new EntityNotFoundException("Followee id not found."));

        followee.getFollowers().add(user);
        userRepository.save(followee);
    }

    @Override
    @Transactional
    public void unfollowUser(Long userId, Long followeeId) throws EntityNotFoundException {
        UserE user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        UserE followee = userRepository
                .findById(followeeId)
                .orElseThrow(() -> new EntityNotFoundException("Followee id not found."));

        followee.getFollowers().remove(user);
        userRepository.save(followee);
    }


}
