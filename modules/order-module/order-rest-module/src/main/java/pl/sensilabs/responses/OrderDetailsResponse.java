package pl.sensilabs.responses;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;

@Builder
public record OrderDetailsResponse(
    UUID orderId,
    BigDecimal finalPrice,
    Integer totalQuantity,
    String orderStatus,
    Set<OrderItemResponse> orderItems
) {

}
