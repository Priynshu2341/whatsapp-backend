package com.example.whatsaapp.dto.response;

import com.example.whatsaapp.entity.MessageStatus;
import com.example.whatsaapp.entity.MessageType;

import java.time.LocalDateTime;

public record MessageResponse(
        Long id,
        String content,
        Long senderId,
        String email,
        Long chatId,
        LocalDateTime createdAt,
        MessageStatus messageStatus,
        MessageType messageType,
        String mediaUrl
) {
}
