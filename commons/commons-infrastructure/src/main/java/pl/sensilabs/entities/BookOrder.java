package pl.sensilabs.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "books_orders")
public class BookOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID bookOrderId;
  @Column(name = "book_id")
  UUID bookId;
  @Column(name = "order_id")
  UUID orderId;
  Integer quantity;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "book_id", insertable = false, updatable = false)
  Book book;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id", insertable = false, updatable = false)
  OrderEntity order;

  public BookOrder(UUID bookId, UUID orderId, Integer quantity) {
    this.bookId = bookId;
    this.orderId = orderId;
    this.quantity = quantity;
  }
}
