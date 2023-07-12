package com.rafaelom.tamias.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException{

    private HttpStatus httpStatus;
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApiRequestException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
