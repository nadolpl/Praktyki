package pl.sensilabs.postgres.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID orderId;
  BigDecimal price;
  String orderStatus;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "book_order",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  Set<Book> orderedBooks;

  public Order(BigDecimal price, String orderStatus) {
    this.price = price;
    this.orderStatus = orderStatus;
  }
}
