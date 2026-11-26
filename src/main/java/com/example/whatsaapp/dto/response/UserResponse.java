package com.example.whatsaapp.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        Long userId,
        String email,
        String firstname,
        String lastname,
        LocalDateTime accountRegisteredAt
) {
}
