package pl.sensilabs.praktyki.repositories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.praktyki.entities.PublishedBook;

@Repository
public interface PublishedBookRepository extends CrudRepository<PublishedBook, UUID> {

}
