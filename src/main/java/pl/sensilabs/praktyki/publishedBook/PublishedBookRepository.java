package pl.sensilabs.praktyki.publishedBook;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PublishedBookRepository extends JpaRepository<PublishedBook, UUID> { }
