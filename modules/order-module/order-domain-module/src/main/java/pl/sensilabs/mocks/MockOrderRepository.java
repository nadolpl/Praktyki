package pl.sensilabs.mocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import pl.sensilabs.OrderAggregate;
import pl.sensilabs.OrderRepository;

public class MockOrderRepository implements OrderRepository {

  Map<UUID, OrderAggregate> orders = new HashMap<>();

  @Override
  public Optional<OrderAggregate> findOrderById(UUID orderId) {
    return Optional.ofNullable(orders.get(orderId));
  }

  @Override
  public OrderAggregate saveOrder(OrderAggregate order) {
    var newOrder = new OrderAggregate(
        UUID.randomUUID(), order.getBasket(), order.getFinalPrice(), order.getOrderStatus());
    orders.put(newOrder.getOrderId(), newOrder);
    return newOrder;
  }

  @Override
  public void updateOrder(OrderAggregate order) {
    if (orders.containsKey(order.getOrderId())) {
      orders.put(order.getOrderId(), order);
    }
  }

  @Override
  public void deleteOrder(UUID orderId) {
    orders.remove(orderId);
  }
}
