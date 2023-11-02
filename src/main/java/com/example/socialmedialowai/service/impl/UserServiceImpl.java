package com.example.socialmedialowai.service.impl;

import com.example.socialmedialowai.dto.UserRegisterRequest;
import com.example.socialmedialowai.model.UserEntity;
import com.example.socialmedialowai.repository.UserRepository;
import com.example.socialmedialowai.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserEntity getUserByUsername(String username) throws EntityNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(EntityExistsException::new);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityExistsException::new);
    }

    @Override
    public UserEntity createUser(UserRegisterRequest request) throws EntityExistsException {
        if(userRepository.existsByUsernameOrEmail(request.username(), request.email())) {
            throw new EntityExistsException("User already exist");
        }

        UserEntity user = UserEntity.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .posts(emptySet())
                .likedPosts(emptySet())
                .followers(emptySet())
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserRegisterRequest request) throws EntityNotFoundException {
        UserEntity user = userRepository
                .findById(request.id())
                .orElseThrow(EntityNotFoundException::new);
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void followUser(Long userId, Long followeeId) throws EntityNotFoundException {
        UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        UserEntity followee = userRepository
                .findById(followeeId)
                .orElseThrow(() -> new EntityNotFoundException("Followee id not found."));

        followee.getFollowers().add(user);
        userRepository.save(followee);
    }

    @Override
    @Transactional
    public void unfollowUser(Long userId, Long followeeId) throws EntityNotFoundException {
        UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        UserEntity followee = userRepository
                .findById(followeeId)
                .orElseThrow(() -> new EntityNotFoundException("Followee id not found."));

        followee.getFollowers().remove(user);
        userRepository.save(followee);
    }


}
