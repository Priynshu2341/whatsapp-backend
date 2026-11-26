package com.example.whatsaapp.dto.request;

public record TypingIndicatorRequest(
        Long chatId,
        boolean typing
) {
}
