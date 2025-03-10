package pl.sensilabs.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(UUID orderId) {
    super("Order with orderId " + orderId + " not found");
  }
}
