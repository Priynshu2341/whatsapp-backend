package com.example.whatsaapp.dto.response;

public record ChatResponse(
        Long chatId,
        Long otherUserId,
        String otherUserName,
        String lastMessage,
        String chatKey,
        long unread,
        boolean isOtherUserOnline
) {
}
