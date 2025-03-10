package pl.sensilabs;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderBasket {

  private final Set<OrderItem> orderItems;

  public OrderBasket() {
    this.orderItems = new HashSet<>();
  }

  public OrderBasket(Set<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public void addOrderItem(OrderItem orderItem) {
    if(hasBookInBasket(orderItem.bookId)) {
      return; //throw new BookAlreadyInBasketException(orderItem);
    }
    orderItems.add(orderItem);
  }

  private boolean hasBookInBasket(UUID bookId) {
    return orderItems.stream()
        .anyMatch(oi -> oi.bookId.equals(bookId));
  }

  //TODO nie działa
  public BigDecimal recalculateFinalPrice() {
    var finalPrice = BigDecimal.ZERO;

    for (OrderItem orderItem : orderItems) {
      finalPrice = finalPrice.add(
          orderItem.unitPrice.multiply(BigDecimal.valueOf(orderItem.quantity)));
    }

    return finalPrice;
  }
}
