package com.ronit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ronit.beans.ResponseDto;
import com.ronit.exceptions.InvalidOperationException;
import com.ronit.exceptions.UserExceptions;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(UserExceptions.class)
	public ResponseEntity<?> handleUserNotFound(UserExceptions e) {
		ResponseDto responsedto = new ResponseDto(false, "handleUserNotFound" + e.getMessage());
		return new ResponseEntity<ResponseDto>(responsedto, HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<?> handleInvalidOperationException(InvalidOperationException e) {
		ResponseDto responsedto = new ResponseDto(false, "handleUserNotFound" + e.getMessage());
		return new ResponseEntity<ResponseDto>(responsedto, HttpStatus.FORBIDDEN);
	}


}
