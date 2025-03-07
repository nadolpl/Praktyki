package pl.sensilabs.praktyki.bookType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BookTypeRepository extends JpaRepository<BookType, Integer> {
    boolean existsByName(String request);
}
