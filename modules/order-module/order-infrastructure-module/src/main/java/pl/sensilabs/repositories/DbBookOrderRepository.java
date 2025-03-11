package pl.sensilabs.repositories;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.sensilabs.BookOrderRepository;
import pl.sensilabs.BookPriceFetcher;
import pl.sensilabs.OrderItem;
import pl.sensilabs.entities.BookOrder;

@Repository
@RequiredArgsConstructor
public class DbBookOrderRepository implements BookOrderRepository {

  private final JpaBookOrderRepository jpaBookOrderRepository;
  private final BookPriceFetcher bookPriceFetcher;

  @Override
  public void addBookOrder(UUID orderId, UUID bookId, int quantity) {
    jpaBookOrderRepository.save(new BookOrder(orderId, bookId, quantity));
  }

  @Override
  public Set<OrderItem> getOrderBasket(UUID orderId) {
    return jpaBookOrderRepository.findAllByOrderId(orderId).stream()
        .map(c -> new OrderItem(
            c.getBookId(),
            c.getQuantity(),
            bookPriceFetcher.fetch(c.getBookId()).toString()))
        .collect(Collectors.toSet());
  }
}
