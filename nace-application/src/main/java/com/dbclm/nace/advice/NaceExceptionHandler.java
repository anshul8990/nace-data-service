package com.dbclm.nace.advice;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dbclm.nace.dto.exception.ServiceException;

@ControllerAdvice
public class NaceExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = EntityNotFoundException.class)
	protected ResponseEntity<Object> handleNaceNotFound(
			RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Nace object not found";
		return handleExceptionInternal(ex, bodyOfResponse,
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}


	@ExceptionHandler(value=ServiceException.class)
	protected ResponseEntity<Object> handleServiceException(
			ServiceException ex, WebRequest request) {
		String bodyOfResponse = "Empty Nace CSV Data ";
		return handleExceptionInternal(ex, bodyOfResponse,
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}
