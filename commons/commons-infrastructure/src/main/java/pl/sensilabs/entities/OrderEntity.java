package pl.sensilabs.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID orderId;
  BigDecimal finalPrice;
  String orderStatus;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  Set<BookOrder> bookOrders;

  public OrderEntity(BigDecimal finalPrice, String orderStatus) {
    this.finalPrice = finalPrice;
    this.orderStatus = orderStatus;
  }
}
