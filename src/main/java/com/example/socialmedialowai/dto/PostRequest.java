package com.example.socialmedialowai.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record PostRequest(
        Long id,
        @NotEmpty
        String title,
        @NotEmpty
        String body,
        Long author_id,
        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
        LocalDateTime createdUpdatedAt
) {
}
