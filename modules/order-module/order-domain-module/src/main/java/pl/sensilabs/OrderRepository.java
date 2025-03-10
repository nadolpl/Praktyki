package pl.sensilabs;

import java.util.Optional;
import java.util.UUID;

interface OrderRepository {

  Optional<Order> findOrderById(UUID orderId);

  Order saveOrder(Order order);

  void updateOrder(Order order);

  void deleteOrder(UUID orderId);
}
