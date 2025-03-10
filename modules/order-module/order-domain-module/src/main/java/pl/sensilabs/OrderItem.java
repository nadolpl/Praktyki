package pl.sensilabs;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderItem {
  UUID bookId;
  Integer quantity;
  BigDecimal unitPrice;

  public OrderItem(UUID bookId, Integer quantity, String unitPrice) {
    this.bookId = bookId;
    this.quantity = quantity;
    this.unitPrice = new BigDecimal(unitPrice);
  }
}
