package com.example.socialmedialowai.dto.response;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record PostDTO(
        Long id,
        @NotEmpty
        String title,
        @NotEmpty
        String body,
        String author,
        @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
        LocalDateTime createdAt,
        @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
        LocalDateTime updatedAt,
        List<UserDTO> likedBy
) {
}
