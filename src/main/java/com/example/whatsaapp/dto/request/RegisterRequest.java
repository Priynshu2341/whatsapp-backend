package com.example.whatsaapp.dto.request;

public record RegisterRequest(
        String email,
        String password,
        String firstname,
        String lastname
) {
}
