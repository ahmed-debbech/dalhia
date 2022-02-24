package tn.dalhia.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import tn.dalhia.response.ErrorMessage;


@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = {UserServiceException.class}) //handli UserServiceException
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex , WebRequest request) {
		
		ErrorMessage errorMessage = new ErrorMessage(new Date() , ex.getMessage()); //error message eli aamelneh ahna fih timestamp w message

		return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR); //kifeh todhher f postman
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherException(Exception ex , WebRequest request) { //handli other Exceptions
		ErrorMessage errorMessage = new ErrorMessage(new Date(),ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
