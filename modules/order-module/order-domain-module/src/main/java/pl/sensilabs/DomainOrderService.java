package pl.sensilabs;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DomainOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final BookPriceFetcher bookPriceFetcher;

  @Override
  public Order createOrder() {
    return orderRepository.saveOrder(new Order());
  }

  @Override
  public Order getOrderById(UUID orderId) {
    return orderRepository.findOrderById(orderId)
        .orElseThrow(/*() -> new OrderNotFoundException(orderId)*/);
  }

  @Override
  public void addBookToOrder(UUID orderId, UUID bookId, int quantity) {
    var order = getOrderById(orderId);
    var bookPrice = bookPriceFetcher.fetch(bookId).toString();
    var orderItem = new OrderItem(bookId, quantity, bookPrice);
    order.addBookToBasket(orderItem);
    orderRepository.saveOrder(order);
  }

  @Override
  public void payForOrder(UUID orderId) {
    var order = getOrderById(orderId);
    order.payForOrder();
    orderRepository.saveOrder(order);
  }

  @Override
  public void shipOrder(UUID orderId) {
    var order = getOrderById(orderId);
    order.shipOrder();
    orderRepository.saveOrder(order);
  }

  @Override
  public void cancelOrder(UUID orderId) {
    var order = getOrderById(orderId);
    order.cancelOrder();
    orderRepository.saveOrder(order);
  }
}
