package com.example.whatsaapp.dto;

import com.example.whatsaapp.entity.OnlineStatus;

public record PresenceEvent(
        String email,
        OnlineStatus onlineStatus
) {
}
