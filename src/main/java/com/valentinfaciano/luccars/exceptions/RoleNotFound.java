package com.valentinfaciano.luccars.exceptions;

public class RoleNotFound extends RuntimeException {
  public RoleNotFound(String message) {
    super(message);
  }
}