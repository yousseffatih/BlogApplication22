package com.BlogAPP.BlogAPPLICATION.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogApiException(String message, HttpStatus status)
    {
       this.message = message;
        this.status = status;
    }

    public BlogApiException(String message, HttpStatus status , String message1)
    {
        super(message);
        this.message = message1;
        this.status = status;
    }
}
