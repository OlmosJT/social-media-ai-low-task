package com.example.socialmedialowai.dto;

import com.example.socialmedialowai.model.UserEntity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

public record PostDTO(
        Long id,
        String title,
        String body,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<String> likedBy
) {
}
