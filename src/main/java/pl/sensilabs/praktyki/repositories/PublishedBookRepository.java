package pl.sensilabs.praktyki.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.praktyki.entities.PublishedBook;

@Repository
public interface PublishedBookRepository extends JpaRepository<PublishedBook, UUID> {

}
