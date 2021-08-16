package com.rvy.store.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(value =  StoreException.class)
	public ResponseStatusException handleFoodDelivaryException(StoreException e) {
		ErrorResponse error = new ErrorResponse("Store_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseStatusException(HttpStatus.NOT_FOUND,error.toString());
	}
	
	@ExceptionHandler(value = { IllegalArgumentException.class})
	public ResponseEntity<ErrorResponse> handleGenericIllegalArgumentException(IllegalArgumentException e) {
		ErrorResponse error = new ErrorResponse("ILLEGAL_ARGUMENT_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.NOT_ACCEPTABLE.value()));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(value = { NumberFormatException.class})
	public ResponseEntity<ErrorResponse> handleGenericNumberFormatException(NumberFormatException e) {
		ErrorResponse error = new ErrorResponse("NUMBER_FORMAT_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.METHOD_NOT_ALLOWED.value()));
		return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}	

}