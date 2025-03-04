package pl.sensilabs.praktyki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.praktyki.entities.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
 boolean existsBookCategoryByCategoryName(String categoryName);
}
