package com.valentinfaciano.luccars.features.user.exceptions;

public class UserProfileNotFoundException extends RuntimeException {
  public UserProfileNotFoundException(String message) {
    super(message);
  }
}
