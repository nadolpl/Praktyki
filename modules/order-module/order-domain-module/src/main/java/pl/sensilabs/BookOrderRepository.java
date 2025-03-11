package pl.sensilabs;

import java.util.Set;
import java.util.UUID;

public interface BookOrderRepository {
  void addBookOrder(UUID orderId, UUID bookId, int quantity);
  Set<OrderItem> getOrderBasket(UUID orderId);
}
