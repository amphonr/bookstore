package com.bookstore.bookstore.exception.handle;

 
public class ExceptionUnauthorized extends RuntimeException {
	 public ExceptionUnauthorized(String msg) {
	        super("Unauthorized Exception : " + msg);
	 }
}
