package pl.sensilabs;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;

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

  public void addBookToBasket(OrderItem item) {
    if(OrderStatus.CONFIRMED != orderStatus) {
      return; //throw new super fajne exception :D
    }

    basket.addOrderItem(item);
    finalPrice = basket.recalculateFinalPrice();
    orderStatus = OrderStatus.PROCESSING;
  }

  public void finishOrder() {
    if(OrderStatus.PROCESSING != orderStatus) {
      return; //exception na potem
    }

    orderStatus = OrderStatus.AWAITING_PAYMENT;
  }

  public void payForOrder() {
    if(OrderStatus.AWAITING_PAYMENT != orderStatus) {
      return; //excepton <3
    }

    orderStatus = OrderStatus.PAID;
  }

  public void shipOrder() {
    if(OrderStatus.PAID != orderStatus) {
      return; //POMocy
    }

    orderStatus = OrderStatus.SHIPPED;
  }

  public void cancelOrder() {
    if(!canCancelOrder()) {
      return; //rzucamy wyjątkami chłopaki
    }

    orderStatus = OrderStatus.CANCELED;
  }

  private boolean canCancelOrder() {
    return orderStatus == OrderStatus.CONFIRMED
        ||  orderStatus == OrderStatus.PROCESSING
        ||  orderStatus == OrderStatus.AWAITING_PAYMENT;
  }
}
