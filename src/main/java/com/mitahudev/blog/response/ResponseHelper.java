package com.mitahudev.blog.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseHelper {
    
    // Success responses
    static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }
    
    static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        return ResponseEntity.ok(ApiResponse.success(data, message));
    }
    
    static <T> ResponseEntity<ApiResponse<T>> success(T data, String message, MetaData meta) {
        return ResponseEntity.ok(ApiResponse.success(data, message, meta));
    }
    
    // Success with pagination
    static <T> ResponseEntity<ApiResponse<List<T>>> successWithPagination(
            List<T> data, long totalCount, int page, int perPage) {
        MetaData meta = MetaData.pagination(totalCount, page, perPage);
        return ResponseEntity.ok(ApiResponse.success(data, "Data retrieved successfully", meta));
    }
    
    // Created responses
    static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(data, "Resource created successfully"));
    }
    
    static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(data, message));
    }
    
    // Error responses
    static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.notFound(message));
    }
    
    static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.badRequest(message));
    }
    
    static <T> ResponseEntity<ApiResponse<T>> internalError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(message));
    }
    
    static <T> ResponseEntity<ApiResponse<T>> customError(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(ApiResponse.error(message, status.value()));
    }
    
    // Validation error response
    static <T> ResponseEntity<ApiResponse<T>> validationError(String message, List<String> details) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .success(false)
                .statusCode(422)
                .message(message)
                .timestamp(java.time.LocalDateTime.now().toString())
                .error(ErrorDetail.builder()
                        .code("VALIDATION_ERROR")
                        .message(message)
                        .details(details)
                        .build())
                .build();
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }
}