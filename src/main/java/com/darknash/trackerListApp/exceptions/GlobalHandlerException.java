package com.darknash.trackerListApp.exceptions;

import com.darknash.trackerListApp.dto.AppResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppResponse> entityNotFound(EntityNotFoundException e) {
        AppResponse response = AppResponse
                .builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .data(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppResponse> duplicate(DuplicateException e) {
        AppResponse response = AppResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .data(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
