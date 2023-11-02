package com.example.socialmedialowai.dto.response;

import jakarta.validation.constraints.*;

public record UserDTO(
        Long id,
        @NotNull(message = "firstName is invalid")
        String firstName,
        @NotNull(message = "firstName is invalid")
        String lastName,
        @NotNull(message = "firstName is invalid")
        String username,
        @Email(message = "invalid email")
        String email
) {
}
