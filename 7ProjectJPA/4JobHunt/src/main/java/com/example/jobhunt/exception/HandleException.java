package com.example.jobhunt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    // Xử lý BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage handlerBadRequestException(BadRequestException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // Xử lý NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage handlerNotFoundException(NotFoundException ex) {
        return new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Xử lý tất cả các exception chưa được khai báo
    @ExceptionHandler(Exception.class)
    public ErrorMessage handlerException(Exception ex) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
