package com.bookstore.bookstore.exception.handle;

 
public class ExceptionDataNotFound extends RuntimeException {
	 public ExceptionDataNotFound(String name, Object objCriteria) {
	        super(name+" not found  Criteria : " + objCriteria.toString());
	 }
}
