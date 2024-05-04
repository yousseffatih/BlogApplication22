package com.BlogAPP.BlogAPPLICATION.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.BlogAPP.BlogAPPLICATION.playload.ErrorDetails;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundException exception , WebRequest webRequest) 
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handlerBlogApiException(BlogApiException exception , WebRequest webRequest) 
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handlerGlobalException(Exception exception , WebRequest webRequest) 
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // protected ResponseEntity<Object> handleValidationExceptions(
	// 		MethodArgumentNotValidException ex) {
    //             Map<String, String> errors = new HashMap<>();
    //             ex.getBindingResult().getAllErrors().forEach((error) -> {
    //                 String fieldName = ((FieldError)error).getField();
    //                 String message = error.getDefaultMessage();
    //                 errors.put(fieldName , message);
    //             });
    //             return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    //         }

    @SuppressWarnings("null")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                  Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach((error) -> {
                    String fieldName = ((FieldError)error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName , message);
                });
                return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }
}
