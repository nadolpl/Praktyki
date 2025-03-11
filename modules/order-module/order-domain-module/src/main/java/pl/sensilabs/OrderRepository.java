package pl.sensilabs;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

  Optional<Order> findOrderById(UUID orderId);

  UUID saveOrder(Order order);

  void updateOrder(Order order);

  void deleteOrder(UUID orderId);
}
