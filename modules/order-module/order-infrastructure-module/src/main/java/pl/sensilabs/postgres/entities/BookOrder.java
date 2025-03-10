package pl.sensilabs.postgres.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class BookOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID bookOrderId;
  UUID bookId;
  UUID orderId;
  Integer quantity;
}
