package pl.sensilabs;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.exceptions.OrderNotFoundException;

@Service
@RequiredArgsConstructor
public class DomainOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final BookPriceFetcher bookPriceFetcher;

  @Override
  public OrderAggregate createOrder() {
    return orderRepository.saveOrder(new OrderAggregate());
  }

  @Override
  public OrderAggregate getOrderById(UUID orderId) {
    return orderRepository.findOrderById(orderId)
        .orElseThrow(() -> new OrderNotFoundException(orderId));
  }

  @Override
  public void addBookToOrder(UUID orderId, UUID bookId, int quantity) {
    var order = getOrderById(orderId);
    var bookPrice = bookPriceFetcher.fetch(bookId).toString();
    var orderItem = new OrderItem(bookId, quantity, bookPrice);
    order.addBookToBasket(orderItem);
    orderRepository.updateOrder(order);
  }

  @Override
  public void finishOrder(UUID orderId) {
    var order = getOrderById(orderId);
    order.finishOrder();
    orderRepository.updateOrder(order);
  }

  @Override
  public void payForOrder(UUID orderId) {
    var order = getOrderById(orderId);
    order.payForOrder();
    orderRepository.updateOrder(order);
  }

  @Override
  public void shipOrder(UUID orderId) {
    var order = getOrderById(orderId);
    order.shipOrder();
    orderRepository.updateOrder(order);
  }

  @Override
  public void cancelOrder(UUID orderId) {
    var order = getOrderById(orderId);
    order.cancelOrder();
    orderRepository.updateOrder(order);
  }
}
