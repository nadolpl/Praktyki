package pl.sensilabs.praktyki.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.requests.PublisherRequest;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

}
