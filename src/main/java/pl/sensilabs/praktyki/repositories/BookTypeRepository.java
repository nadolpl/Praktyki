package pl.sensilabs.praktyki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.requests.BookTypeRequest;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Integer> {
    boolean existsByBookTypeName(String bookTypeRequest);
}
