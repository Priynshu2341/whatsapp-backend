package com.example.whatsaapp.dto;

public record PresenceEvent(
        String email,
        OnlineStatus onlineStatus
) {
}
