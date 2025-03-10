package pl.sensilabs.postgres.repositories;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sensilabs.BookPriceFetcher;
import pl.sensilabs.Order;
import pl.sensilabs.OrderBasket;
import pl.sensilabs.OrderItem;
import pl.sensilabs.OrderRepository;
import pl.sensilabs.OrderStatus;
import pl.sensilabs.postgres.entities.BookOrder;
import pl.sensilabs.postgres.entities.OrderEntity;

@Repository
@RequiredArgsConstructor
public class DbOrderRepository implements OrderRepository {

  private final JpaOrderRepository jpaOrderRepository;
  private final JpaBookOrderRepository jpaBookOrderRepository;
  private final BookPriceFetcher bookPriceFetcher;

  @Override
  public Optional<Order> findOrderById(UUID orderId) {

    return jpaOrderRepository.findById(orderId).map(o -> {
      var orderBasket = new OrderBasket(
          jpaBookOrderRepository.findAllByOrderId(orderId)
              .stream().map(bookOrder -> new OrderItem(
                  bookOrder.getBookId(),
                  bookOrder.getQuantity(),
                  bookPriceFetcher.fetch(bookOrder.getBookId()).toString()
              )).collect(Collectors.toSet()));
      return new Order(
          orderId, orderBasket, o.getFinalPrice(), OrderStatus.valueOf(o.getOrderStatus()));
    });
  }

  @Override
  public Order saveOrder(Order order) {
    var entity = new OrderEntity();
    entity.setFinalPrice(order.getFinalPrice());
    entity.setOrderStatus(order.getOrderStatus().toString());
    var newId = jpaOrderRepository.save(entity).getOrderId();
    return new Order(newId, order.getBasket(), order.getFinalPrice(), order.getOrderStatus());
  }

  @Override
  @Transactional
  public void updateOrder(Order order) {
    jpaOrderRepository.findById(order.getOrderId()).ifPresent(o -> {
      o.setFinalPrice(order.getFinalPrice());
      o.setOrderStatus(order.getOrderStatus().toString());

      order.getBasket().getOrderItems().forEach(oi -> {
        if (!jpaBookOrderRepository
            .existsByOrderIdAndBookId(order.getOrderId(), oi.getBookId())) {
          jpaBookOrderRepository.save(
              new BookOrder(oi.getBookId(), order.getOrderId(), oi.getQuantity()));
        }
      });
    });
  }

  @Override
  public void deleteOrder(UUID orderId) {
    jpaOrderRepository.deleteById(orderId);
  }
}
