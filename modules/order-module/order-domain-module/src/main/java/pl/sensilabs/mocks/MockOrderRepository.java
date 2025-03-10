package pl.sensilabs.mocks;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import pl.sensilabs.Order;
import pl.sensilabs.OrderRepository;

class MockOrderRepository implements OrderRepository {

  Map<UUID, Order>

  @Override
  public Optional<Order> findOrderById(UUID orderId) {
    return Optional.empty();
  }

  @Override
  public Order saveOrder(Order order) {
    return null;
  }

  @Override
  public void updateOrder(Order order) {

  }

  @Override
  public void deleteOrder(UUID orderId) {

  }
}
