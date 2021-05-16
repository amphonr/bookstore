package com.bookstore.bookstore.exception.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    protected HttpStatus status;
    protected String message;
    public ErrorResponse(HttpStatus status,
                         String message){
        this.status = status;
        this.message = message;
    }
}
