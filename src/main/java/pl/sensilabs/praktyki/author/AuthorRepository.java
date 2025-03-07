package pl.sensilabs.praktyki.author;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface AuthorRepository extends JpaRepository<Author, UUID> {
  List<AuthorIdOnly> findAllBy();

  interface AuthorIdOnly {
    UUID getId();
  }
}
