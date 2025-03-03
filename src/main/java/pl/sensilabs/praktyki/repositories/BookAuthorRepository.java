package pl.sensilabs.praktyki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sensilabs.praktyki.entities.BookAuthor;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {

}
