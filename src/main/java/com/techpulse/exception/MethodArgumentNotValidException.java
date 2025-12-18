package com.techpulse.exception;

import org.springframework.http.HttpStatus;

public class MethodArgumentNotValidException extends RuntimeException {
    private HttpStatus status;

    public MethodArgumentNotValidException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
