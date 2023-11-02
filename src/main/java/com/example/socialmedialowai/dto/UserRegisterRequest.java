package com.example.socialmedialowai.dto;

import jakarta.validation.constraints.*;

public record UserRegisterRequest(
        Long id,
        @NotNull(message = "firstName is invalid")
        String firstName,
        @NotNull(message = "firstName is invalid")
        String lastName,
        @NotNull(message = "firstName is invalid")
        String username,
        @Email(message = "invalid email")
        String email,
        @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters long")
        String password
) {
}
