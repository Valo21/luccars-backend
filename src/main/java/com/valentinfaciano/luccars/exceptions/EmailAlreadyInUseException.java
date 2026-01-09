package com.valentinfaciano.luccars.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {
  public EmailAlreadyInUseException(String message) {
    super(message);
  }
}