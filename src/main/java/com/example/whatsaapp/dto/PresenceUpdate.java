package com.example.whatsaapp.dto;

public record PresenceUpdate(
        String email,
        OnlineStatus onlineStatus
) {
}
