package com.server.SongServer.controllers.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleNoSuchElementException(BindingResult bindingResult) {
        String errorMessage = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::toString)
                .collect(Collectors.joining("\n"));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, NoSuchElementException.class})
    public ResponseEntity<String> handleIllegalArgumentException(Exception exception) {
        String errorMessage = Arrays.toString(exception.getStackTrace());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
