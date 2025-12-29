package com.vehicleManagmentSystem.entity.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // this method handling validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

    // this method handling nested list json validiation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String,String> handleConstraintViolationException(ConstraintViolationException exc){
        Map<String,String> errorMap = new HashMap<>();
        exc.getConstraintViolations().forEach(error ->{
            errorMap.put(error.getPropertyPath().toString(),error.getMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = "Please Enter valid Mobile No., Resident Type and Vehicle Type ";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // This exception handle user not found exception.
    @ExceptionHandler(UserNotFoundByException.class)
    public ResponseEntity<String> handleUserNotFoundByException(UserNotFoundByException userNotFoundByException){
        String errorMessage = userNotFoundByException.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    //This method handle Empty inputs from user.
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<String> handleInvalidParameterException(InvalidParameterException invalidParameterException){
        String errorMessage = invalidParameterException.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }




}
