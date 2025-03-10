package pl.sensilabs;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.sensilabs.exceptions.InvalidOrderStateException;

@Getter //możliwie nie potrzebne i wystarczył by getter dla ID ale zobaczymy
@AllArgsConstructor //do mapowania z encji
public class OrderAggregate {
  private UUID orderId;
  private final OrderBasket basket;
  private BigDecimal finalPrice;
  private OrderStatus orderStatus;

  public OrderAggregate() {
    basket = new OrderBasket();
    finalPrice = BigDecimal.ZERO;
    orderStatus = OrderStatus.CONFIRMED;
  }

  public void addBookToBasket(OrderItem item) {
    validateState(OrderStatus.CONFIRMED, "Cannot add products to order once its finished.");

    basket.addOrderItem(item);
    finalPrice = basket.recalculateFinalPrice();
    orderStatus = OrderStatus.PROCESSING;
  }

  public void finishOrder() {
   validateState(OrderStatus.PROCESSING, "Cannot finish empty order.");

    orderStatus = OrderStatus.AWAITING_PAYMENT;
  }

  public void payForOrder() {
    validateState(OrderStatus.AWAITING_PAYMENT, "Cannot pay for unfinished order.");

    orderStatus = OrderStatus.PAID;
  }

  public void shipOrder() {
    validateState(OrderStatus.PAID, "Cannot ship order until its paid.");

    orderStatus = OrderStatus.SHIPPED;
  }

  public void cancelOrder() {
    if(!canCancelOrder()) {
      throw new  InvalidOrderStateException("Cannot cancel order awaiting for payment.");
    }

    orderStatus = OrderStatus.CANCELED;
  }

  private boolean canCancelOrder() {
    return orderStatus == OrderStatus.CONFIRMED
        ||  orderStatus == OrderStatus.PROCESSING
        ||  orderStatus == OrderStatus.AWAITING_PAYMENT;
  }

  private void validateState(OrderStatus expectedStatus, String exceptionMessage) {
    if(orderStatus != expectedStatus) {
      throw new InvalidOrderStateException(exceptionMessage);
    }
  }
}
