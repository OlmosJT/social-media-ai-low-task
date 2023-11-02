package com.example.socialmedialowai.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreationDTO(
        Long id,
        @NotEmpty
        @NotNull
        String firstName,
        @NotEmpty
        @NotNull
        String lastName,
        @NotEmpty
        @NotNull
        String username,
        @Email(message = "Invalid email")
        String email,
        @Size(min = 6, max = 20, message = "Password length should be minimum 6 and maximum 20")
        String password
) {
}
