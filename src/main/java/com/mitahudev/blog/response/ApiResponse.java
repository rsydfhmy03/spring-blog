package com.mitahudev.blog.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    
    @JsonProperty("success")
    private boolean success;
    
    @JsonProperty("status_code")
    private int statusCode;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("timestamp")
    private String timestamp;
    
    @JsonProperty("data")
    private T data;
    
    @JsonProperty("error")
    private ErrorDetail error;
    
    @JsonProperty("meta")
    private MetaData meta;
    
    // Static factory methods untuk success responses
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(200)
                .message("Success")
                .timestamp(getCurrentTimestamp())
                .data(data)
                .build();
    }
    
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(200)
                .message(message)
                .timestamp(getCurrentTimestamp())
                .data(data)
                .build();
    }
    
    public static <T> ApiResponse<T> success(T data, String message, int statusCode) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(statusCode)
                .message(message)
                .timestamp(getCurrentTimestamp())
                .data(data)
                .build();
    }
    
    public static <T> ApiResponse<T> success(T data, String message, MetaData meta) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(200)
                .message(message)
                .timestamp(getCurrentTimestamp())
                .data(data)
                .meta(meta)
                .build();
    }
    
    // Static factory methods untuk error responses
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .statusCode(500)
                .message(message)
                .timestamp(getCurrentTimestamp())
                .error(ErrorDetail.builder()
                        .code("INTERNAL_SERVER_ERROR")
                        .message(message)
                        .build())
                .build();
    }
    
    public static <T> ApiResponse<T> error(String message, int statusCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .statusCode(statusCode)
                .message(message)
                .timestamp(getCurrentTimestamp())
                .error(ErrorDetail.builder()
                        .code(getErrorCodeFromStatus(statusCode))
                        .message(message)
                        .build())
                .build();
    }
    
    public static <T> ApiResponse<T> error(String message, int statusCode, String errorCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .statusCode(statusCode)
                .message(message)
                .timestamp(getCurrentTimestamp())
                .error(ErrorDetail.builder()
                        .code(errorCode)
                        .message(message)
                        .build())
                .build();
    }
    
    // Not found response
    public static <T> ApiResponse<T> notFound(String message) {
        return error(message, 404, "NOT_FOUND");
    }
    
    // Bad request response
    public static <T> ApiResponse<T> badRequest(String message) {
        return error(message, 400, "BAD_REQUEST");
    }
    
    // Created response
    public static <T> ApiResponse<T> created(T data, String message) {
        return success(data, message, 201);
    }
    
    // Helper methods
    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
    private static String getErrorCodeFromStatus(int statusCode) {
        return switch (statusCode) {
            case 400 -> "BAD_REQUEST";
            case 401 -> "UNAUTHORIZED";
            case 403 -> "FORBIDDEN";
            case 404 -> "NOT_FOUND";
            case 409 -> "CONFLICT";
            case 422 -> "UNPROCESSABLE_ENTITY";
            case 500 -> "INTERNAL_SERVER_ERROR";
            default -> "UNKNOWN_ERROR";
        };
    }
}