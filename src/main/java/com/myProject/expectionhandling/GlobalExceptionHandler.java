package com.myProject.expectionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException exception) {
        Map<String,Object> map = new HashMap<>();
        map.put("message",exception.getMessage());
        map.put("status", HttpStatus.NOT_FOUND.value());
        map.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception exception) {
        Map<String,Object> map = new HashMap<>();
        map.put("message",exception.getMessage());
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
