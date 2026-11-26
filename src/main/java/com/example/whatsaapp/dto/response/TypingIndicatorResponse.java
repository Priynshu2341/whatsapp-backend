package com.example.whatsaapp.dto.response;

public record TypingIndicatorResponse(
        Long chatId,
        Long senderId,
        boolean typing
) {
}
