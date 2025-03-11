package pl.sensilabs.repositories;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sensilabs.BookOrderRepository;
import pl.sensilabs.Order;
import pl.sensilabs.OrderBasket;
import pl.sensilabs.OrderRepository;
import pl.sensilabs.OrderStatus;
import pl.sensilabs.entities.OrderEntity;

@Repository
@RequiredArgsConstructor
public class DbOrderRepository implements OrderRepository {

  private final JpaOrderRepository jpaOrderRepository;
  private final BookOrderRepository bookOrderRepository;

  @Override
  public Optional<Order> findOrderById(UUID orderId) {
    return jpaOrderRepository.findById(orderId).map(o -> {
      var orderBasket = new OrderBasket(bookOrderRepository.getOrderBasket(orderId));
      return new Order(
          orderId, orderBasket, OrderStatus.valueOf(o.getOrderStatus()));
    });
  }

  @Override
  public UUID saveOrder(Order order) {
    var entity = new OrderEntity();
    entity.setFinalPrice(order.getFinalPrice());
    entity.setOrderStatus(order.getOrderStatus().toString());
    return jpaOrderRepository.save(entity).getOrderId();
  }

  @Override
  @Transactional
  public void updateOrder(Order order) {
    jpaOrderRepository.findById(order.getOrderId()).ifPresent(o -> {
      o.setFinalPrice(order.getFinalPrice());
      o.setOrderStatus(order.getOrderStatus().toString());
    });
  }

  @Override
  public void deleteOrder(UUID orderId) {
    jpaOrderRepository.deleteById(orderId);
  }
}

//TODO pozmieniać nazwy w bazie danych i dodać ceny do książek
//TODO dodać więcej możliwości w kontrollerze i DTO z mapperami
//TODO może kontroller który w body przyjmuje polecenia będzie ciekawy :')
