package pl.sensilabs.praktyki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.praktyki.entities.BookType;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Integer> {
}
