package pl.sensilabs;

import java.util.UUID;

public interface OrderService {

  Order createOrder();

  Order getOrderById(UUID orderId);

  void addBookToOrder(UUID orderId, UUID bookId, int quantity);

  void payForOrder(UUID orderId); //mega udawanka

  void shipOrder(UUID  orderId);

  void cancelOrder(UUID orderId);

  //  potem można poszerzyć, bo nie wiem co jeszcze będzie potrzebne
  //  śmiało zmieniajcie/usuwajcie/dodawajcie funkcje jeśli coś można
  //  zrobić lepiej
}
