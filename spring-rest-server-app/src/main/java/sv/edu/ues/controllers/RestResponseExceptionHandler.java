package sv.edu.ues.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sv.edu.ues.services.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<Object> notFoundHandler(Exception exception, WebRequest request){
		return new ResponseEntity<Object>("Resourse not found",new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
}
