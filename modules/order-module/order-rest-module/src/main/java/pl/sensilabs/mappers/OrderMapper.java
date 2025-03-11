package pl.sensilabs.mappers;

import java.util.stream.Collectors;
import pl.sensilabs.Order;
import pl.sensilabs.OrderItem;
import pl.sensilabs.responses.OrderDetailsResponse;
import pl.sensilabs.responses.OrderItemResponse;

public interface OrderMapper {

  static OrderDetailsResponse toOrderDetailsResponse(Order order) {
    return OrderDetailsResponse.builder()
        .orderId(order.getOrderId())
        .finalPrice(order.getFinalPrice())
        .totalQuantity(order.getBasket().getOrderItems().stream()
            .mapToInt(OrderItem::getQuantity).sum())
        .orderStatus(order.getOrderStatus().toString())
        .orderItems(order.getBasket().getOrderItems().stream()
            .map(o -> new OrderItemResponse(o.getBookId(), o.getQuantity()))
            .collect(Collectors.toSet()))
        .build();
  }
}
