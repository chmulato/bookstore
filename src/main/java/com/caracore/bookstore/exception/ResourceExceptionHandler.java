package com.caracore.bookstore.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandandError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
		StandandError error = new StandandError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis() ,e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandandError> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		StandandError error = new StandandError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis() ,e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
