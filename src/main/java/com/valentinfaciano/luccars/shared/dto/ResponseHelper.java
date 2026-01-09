package com.valentinfaciano.luccars.shared.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class ResponseHelper {

  public static <T> ResponseEntity<AppResponseDTO<T>> success(T data, String message) {
    AppResponseDTO<T> response = new AppResponseDTO<>();
    response.setSuccess(true);
    response.setMessage(message);
    response.setData(data);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public static <T> ResponseEntity<AppResponseDTO<T>> created(T data, String message) {
    AppResponseDTO<T> response = new AppResponseDTO<>();
    response.setSuccess(true);
    response.setMessage(message);
    response.setData(data);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  public static <T> ResponseEntity<AppResponseDTO<T>> error(List<String> errors, String message, HttpStatus status) {
    AppResponseDTO<T> response = new AppResponseDTO<>();
    response.setSuccess(false);
    response.setMessage(message);
    response.setErrors(errors);
    return new ResponseEntity<>(response, status);
  }
}