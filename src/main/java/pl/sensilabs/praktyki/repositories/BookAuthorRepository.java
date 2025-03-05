package pl.sensilabs.praktyki.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sensilabs.praktyki.entities.BookAuthor;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
  void deleteAllByBookId(UUID bookId);
}
