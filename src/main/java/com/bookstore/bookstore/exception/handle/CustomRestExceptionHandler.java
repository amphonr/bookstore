package com.bookstore.bookstore.exception.handle;

import com.bookstore.bookstore.exception.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Exception.class ,Exception500.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
	    ApiError apiError = new ApiError(
	      HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(ExceptionUnauthorized.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Object> handleUnauthorized(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), "Unauthorized");
		return new ResponseEntity<Object>(
				apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(ExceptionDataNotFound.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> handleDataNotFound(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NO_CONTENT, ex.getLocalizedMessage(), "Data not found");
		return new ResponseEntity<Object>(
				apiError, new HttpHeaders(), apiError.getStatus());
	}
}
