package pl.sensilabs.praktyki.bookAuthor;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
  void deleteAllByBookId(UUID bookId);
}
