package org.nabius.carservice.controllers;

import org.nabius.carservice.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDto> handleIllegalArgumentError(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(ErrorDto.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String details = e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField().concat(" ").concat(error.getDefaultMessage()))
                .collect(Collectors.joining(".\n"));

        return ResponseEntity
                .badRequest()
                .body(ErrorDto.builder()
                        .message(details)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
