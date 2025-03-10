package pl.sensilabs;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class OrderItem {
  UUID bookId;
  Integer quantity;
  BigDecimal unitPrice;
}
