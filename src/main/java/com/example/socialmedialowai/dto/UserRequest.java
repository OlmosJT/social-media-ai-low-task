package com.example.socialmedialowai.dto;

public record UserRequest(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String password
) {
}
