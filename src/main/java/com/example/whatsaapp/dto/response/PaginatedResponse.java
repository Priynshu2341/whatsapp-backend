package com.example.whatsaapp.dto.response;

import com.example.whatsaapp.dto.MessageCursor;

import java.util.List;

public record PaginatedResponse<T>(
        List<T> content,
        Boolean hasMore,
        MessageCursor nextCursor,
        int elementsInPage
) {
}
