package com.example.whatsaapp.dto.request;

import com.example.whatsaapp.entity.MessageType;
import jakarta.validation.constraints.NotNull;

public record MessageRequest(
        @NotNull
        Long receiverId,
        @NotNull
        String content,
        String mediaUrl,
        MessageType messageType
) {
}
