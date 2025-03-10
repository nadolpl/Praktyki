package pl.sensilabs;

import java.util.Map;
import java.util.UUID;

class OrderBasket {
  Map<UUID, OrderItem>  orderItems;

  public OrderBasket(Map<UUID, OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public void addOrderItem(OrderItem orderItem) {

  }
}
