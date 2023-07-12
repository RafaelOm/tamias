package com.rafaelom.tamias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value= {ApiRequestException.class})
    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException e){

        HttpStatus badRequest = e.getHttpStatus() != null ? e.getHttpStatus() : HttpStatus.BAD_REQUEST;
        ApiException apiException=new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException,badRequest);
    }
}
