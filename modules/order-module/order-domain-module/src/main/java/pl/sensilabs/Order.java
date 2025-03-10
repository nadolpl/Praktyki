package pl.sensilabs;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Order {
  UUID orderId;
  OrderBasket basket;
  BigDecimal finalPrice;
  OrderStatus orderStatus;

  public Order() {
    this.orderId = UUID.randomUUID();

  }
}
