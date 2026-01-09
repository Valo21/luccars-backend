package com.valentinfaciano.luccars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.valentinfaciano.luccars.shared.dto.AppResponseDTO;
import com.valentinfaciano.luccars.shared.dto.ResponseHelper;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponseDTO<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseHelper.error(errors, "Validation failed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AppResponseDTO<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseHelper.error(List.of(ex.getMessage()), "Request body is missing or invalid",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AppResponseDTO<String>> handleUserNotFound(UserNotFoundException ex) {
        return ResponseHelper.error(
                List.of(ex.getMessage()),
                "User not found",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<AppResponseDTO<String>> handleEmailAlreadyInUse(EmailAlreadyInUseException ex) {
        return ResponseHelper.error(
                List.of(ex.getMessage()),
                "Email already in use",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<AppResponseDTO<String>> handleInvalidPassword(InvalidPasswordException ex) {
        return ResponseHelper.error(
                List.of(ex.getMessage()),
                "Invalid password",
                HttpStatus.BAD_REQUEST);
    }
}
