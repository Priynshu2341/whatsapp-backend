package com.example.whatsaapp.dto;

import java.time.LocalDateTime;

public record MessageCursor(
        LocalDateTime createdAt,
        Long id
) {
}
