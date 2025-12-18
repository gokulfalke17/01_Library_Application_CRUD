package com.techpulse.exception;

import com.techpulse.response.ApiResponse;
import com.techpulse.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .status(ex.getStatus())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, ex.getStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message("Validation Failed")
                .status(HttpStatus.BAD_REQUEST)
                .data(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .message("An unexpected error occurred.")
                .status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
