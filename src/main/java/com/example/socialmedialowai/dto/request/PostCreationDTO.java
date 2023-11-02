package com.example.socialmedialowai.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record PostCreationDTO(
        @NotNull
        @Min(0)
        Long id,
        @NotEmpty
        String title,
        @NotEmpty
        String body,
        Long authorId,
        @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
        LocalDateTime createdAt
) {
}
