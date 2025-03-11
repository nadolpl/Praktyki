package pl.sensilabs.repositories;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.entities.Book;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, UUID> {

  BookPrice findByBookId(UUID bookId);

  interface BookPrice {
    BigDecimal getPrice();
  }
}
