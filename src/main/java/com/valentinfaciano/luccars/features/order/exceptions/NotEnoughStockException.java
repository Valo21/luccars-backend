package com.valentinfaciano.luccars.features.order.exceptions;

public class NotEnoughStockException extends RuntimeException {
  public NotEnoughStockException(String message) {
    super(message);
  }
}
