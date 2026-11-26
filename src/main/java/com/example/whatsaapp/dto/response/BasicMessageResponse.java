package com.example.whatsaapp.dto.response;

import com.example.whatsaapp.entity.MessageStatus;
import com.example.whatsaapp.entity.MessageType;

import java.time.LocalDateTime;

public record BasicMessageResponse(
        Long id,
        String content,
        Long senderId,
        LocalDateTime createdAt,
        MessageStatus messageStatus,
        MessageType messageType,
        String mediaUrl
) {
}
