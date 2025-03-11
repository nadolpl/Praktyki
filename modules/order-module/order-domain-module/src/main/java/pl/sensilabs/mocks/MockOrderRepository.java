package pl.sensilabs.mocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import pl.sensilabs.Order;
import pl.sensilabs.OrderRepository;

public class MockOrderRepository implements OrderRepository {

  Map<UUID, Order> orders = new HashMap<>();

  @Override
  public Optional<Order> findOrderById(UUID orderId) {
    return Optional.ofNullable(orders.get(orderId));
  }

  @Override
  public UUID saveOrder(Order order) {
    var newOrder = new Order(
        UUID.randomUUID(), order.getBasket(), order.getOrderStatus());
    orders.put(newOrder.getOrderId(), newOrder);
    return newOrder.getOrderId();
  }

  @Override
  public void updateOrder(Order order) {
    if (orders.containsKey(order.getOrderId())) {
      orders.put(order.getOrderId(), order);
    }
  }

  @Override
  public void deleteOrder(UUID orderId) {
    orders.remove(orderId);
  }
}
