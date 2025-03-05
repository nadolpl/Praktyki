package pl.sensilabs.praktyki.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.entities.BookCategory;

@DataJpaTest
class BookRepositoryTest {

  @Autowired
  BookRepository bookRepository;
  @Autowired
  BookCategoryRepository bookCategoryRepository;

  @BeforeEach
  void setUp() {
    var bookCategory = new BookCategory();
    bookCategory.setCategoryName("Fantasy");
    bookCategoryRepository.save(bookCategory);
  }

  @AfterEach
  public void tearDown() {
    bookRepository.deleteAll();
    bookCategoryRepository.deleteAll();
  }

  @Test
  void findAll_shouldReturnAllBooks() {
    final int FANTASY_CATEGORY_ID = 1;
    bookRepository.saveAll(List.of(
        new Book("Hobbit", 345, FANTASY_CATEGORY_ID),
        new Book("Pan Tadeusz", 555, FANTASY_CATEGORY_ID),
        new Book("Czesio", 123, FANTASY_CATEGORY_ID)));

    var expectedBooks = bookRepository.findAll();

    assertThat(expectedBooks)
        .as("Books should not be null or empty")
        .isNotNull()
        .isNotEmpty();
  }
}
