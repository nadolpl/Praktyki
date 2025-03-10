package pl.sensilabs;

import java.util.UUID;

public interface OrderService {

  OrderAggregate createOrder();

  OrderAggregate getOrderById(UUID orderId);

  void addBookToOrder(UUID orderId, UUID bookId, int quantity);

  void finishOrder(UUID orderId);

  void payForOrder(UUID orderId); //mega udawanka

  void shipOrder(UUID  orderId);

  void cancelOrder(UUID orderId);
}
