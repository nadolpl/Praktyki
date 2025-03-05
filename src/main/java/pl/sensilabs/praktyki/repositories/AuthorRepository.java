package pl.sensilabs.praktyki.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.praktyki.entities.Author;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
  List<AuthorIdOnly> findAllBy();

  interface AuthorIdOnly {
    UUID getAuthorId();
  }
}
