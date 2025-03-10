package pl.sensilabs;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

  Optional<OrderAggregate> findOrderById(UUID orderId);

  OrderAggregate saveOrder(OrderAggregate order);

  void updateOrder(OrderAggregate order);

  void deleteOrder(UUID orderId);
}
