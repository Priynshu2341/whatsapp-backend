package com.example.whatsaapp.dto;

import com.example.whatsaapp.entity.OnlineStatus;

public record PresenceUpdate(
        String email,
        OnlineStatus onlineStatus
) {
}
