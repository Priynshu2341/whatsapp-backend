package com.example.whatsaapp.dto.response;

public record LoginResponse(  String accessToken,
                              String refreshToken,
                              Long userId) {
}
