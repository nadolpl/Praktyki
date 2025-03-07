package pl.sensilabs.praktyki.bookCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    boolean existsBookCategoryByName(String request);
}
