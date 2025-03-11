package pl.sensilabs.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.entities.BookOrder;

@Repository
public interface JpaBookOrderRepository extends JpaRepository<BookOrder, UUID> {

  List<BookOrder> findAllByOrderId(UUID orderId);

  boolean existsByOrderIdAndBookId(UUID orderId, UUID bookId);

  BookOrder findByOrderIdAndBookId(UUID orderId, UUID bookId);
}
