package com.librarymanagment.LibraryManagment.exception;

import com.librarymanagment.LibraryManagment.dto.HttpDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpDTO> handelNotFound(EntityNotFoundException exc){
        HttpDTO error = new HttpDTO(exc.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(JsonPatchProcessingException.class)
    public ResponseEntity<HttpDTO> handelPatchProcess(JsonPatchProcessingException exc){
        HttpDTO error = new HttpDTO(exc.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
