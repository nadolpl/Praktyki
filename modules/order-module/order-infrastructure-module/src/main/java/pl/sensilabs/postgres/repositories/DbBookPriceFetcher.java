package pl.sensilabs.postgres.repositories;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.sensilabs.BookPriceFetcher;

@Repository
@RequiredArgsConstructor
public class DbBookPriceFetcher implements BookPriceFetcher {

  private final JpaBookRepository jpaBookRepository;

  @Override
  public BigDecimal fetch(UUID bookId) {
    return jpaBookRepository.findByBookId(bookId).getPrice();
  }
}
