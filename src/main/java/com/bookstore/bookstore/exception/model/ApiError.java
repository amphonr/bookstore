package com.bookstore.bookstore.exception.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiError extends ErrorResponse {
	    private List<String> errors;
	    public ApiError(HttpStatus status, String message, List<String> errors) {
	        super(status,message);
	        this.status = status;
	        this.message = message;
	        this.errors = errors;
	    }
	    public ApiError(HttpStatus status, String message, String error) {
			super(status,message);
	        this.status = status;
	        this.message = message;
	        errors = Arrays.asList(error);
	    }
}
