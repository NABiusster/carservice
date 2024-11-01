package org.nabius.carservice.controllers;

import org.nabius.carservice.Dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDTO> handleIllegalArgumentError(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(ErrorDTO.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String details = e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField().concat(" ").concat(Objects.requireNonNull(error.getDefaultMessage())))
                .collect(Collectors.joining(".\n"));

        return ResponseEntity
                .badRequest()
                .body(ErrorDTO.builder()
                        .message(details)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
