package pl.sensilabs.exceptions;

import pl.sensilabs.OrderStatus;

public class InvalidOrderStateException extends RuntimeException {
  public InvalidOrderStateException(String message) {
    super(message);
  }
}
