package com.Bikkadit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Bikkadit.Payloads.ApiResponcse;

@RestControllerAdvice
public class GlobleExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ApiResponcse> resourcenotfound(ResourceNotFoundException ex)
	{
		String msg=ex.getMessage();
		
		ApiResponcse apiResponcse=new ApiResponcse(msg,false);
		
		return new ResponseEntity<ApiResponcse>(apiResponcse,HttpStatus.NOT_FOUND);
		
	}

}
