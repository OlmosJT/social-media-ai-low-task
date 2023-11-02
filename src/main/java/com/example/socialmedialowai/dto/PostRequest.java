package com.example.socialmedialowai.dto;

import java.time.LocalDateTime;

public record PostRequest(Long id, String title, String body, Long author_id, LocalDateTime createdAt) {
}
