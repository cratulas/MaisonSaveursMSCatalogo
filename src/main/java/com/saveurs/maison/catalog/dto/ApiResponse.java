package com.saveurs.maison.catalog.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiResponse<T> {

    private T data;
    private ApiError error;
    private Instant timestamp;
    private String requestId;

    @Data
    @Builder
    public static class ApiError {
        private String code;
        private String message;
        private Object details;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder()
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> error(String code, String message, Object details) {
        return ApiResponse.<T>builder()
                .error(ApiError.builder()
                        .code(code)
                        .message(message)
                        .details(details)
                        .build())
                .timestamp(Instant.now())
                .build();
    }
}
