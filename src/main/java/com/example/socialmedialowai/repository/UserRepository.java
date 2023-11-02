package com.example.socialmedialowai.repository;

import com.example.socialmedialowai.model.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserE, Long> {
    Optional<UserE> findByUsername(String username);
    Optional<UserE> findByEmail(String email);

    boolean existsByUsernameOrEmail(String username, String email);
}
