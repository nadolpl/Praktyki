package pl.sensilabs;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import pl.sensilabs.exceptions.InvalidOrderStateException;

@Getter
public class Order {

  private UUID orderId;
  private final OrderBasket basket;
  private BigDecimal finalPrice;
  private OrderStatus orderStatus;

  public Order() {
    basket = new OrderBasket();
    finalPrice = BigDecimal.ZERO;
    orderStatus = OrderStatus.CONFIRMED;
  }

  public Order(UUID orderId, OrderBasket basket, OrderStatus orderStatus) {
    this.orderId = orderId;
    this.basket = basket;
    this.finalPrice = basket.recalculateFinalPrice();
    this.orderStatus = orderStatus;
  }

  public void addBookToBasket(OrderItem item) {
    if (!canAddProductToOrder()) {
      throw new InvalidOrderStateException("Order has already been finished and ready for payment");
    }

    basket.addOrderItem(item);
    finalPrice = basket.recalculateFinalPrice();
    orderStatus = OrderStatus.PROCESSING;
  }

  private boolean canAddProductToOrder() {
    return orderStatus == OrderStatus.CONFIRMED || orderStatus == OrderStatus.PROCESSING;
  }

  public void finishOrder() {
    validateState(OrderStatus.PROCESSING, "Cannot finish empty order.");

    orderStatus = OrderStatus.AWAITING_PAYMENT;
  }

  public void continueOrder() {
    validateState(
        OrderStatus.AWAITING_PAYMENT, "Cannot continue order that is unfinished, paid or shipped.");

    orderStatus = OrderStatus.PROCESSING;
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
    if (!canCancelOrder()) {
      throw new InvalidOrderStateException("Cannot cancel order awaiting for payment.");
    }

    orderStatus = OrderStatus.CANCELED;
  }

  private boolean canCancelOrder() {
    return orderStatus == OrderStatus.CONFIRMED
        || orderStatus == OrderStatus.PROCESSING
        || orderStatus == OrderStatus.AWAITING_PAYMENT
        || orderStatus == OrderStatus.SHIPPED;
  }

  private void validateState(OrderStatus expectedStatus, String exceptionMessage) {
    if (orderStatus != expectedStatus) {
      throw new InvalidOrderStateException(exceptionMessage);
    }
  }
}
