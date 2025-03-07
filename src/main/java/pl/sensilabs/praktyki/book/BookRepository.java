package pl.sensilabs.praktyki.book;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BookRepository extends JpaRepository<Book, UUID> {

}
