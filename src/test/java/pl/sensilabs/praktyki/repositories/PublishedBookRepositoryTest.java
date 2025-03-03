package pl.sensilabs.praktyki.repositories;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sensilabs.praktyki.entities.PublishedBook;

@DataJpaTest
class PublishedBookRepositoryTest {

  @Autowired
  private PublishedBookRepository publishedBookRepository;

  @BeforeEach
  public void setUp() {
    //się zobaczy
  }

  @AfterEach
  public void tearDown() {
    publishedBookRepository.deleteAll();
  }

  @Test
  void findAll_shouldReturnAllPublishedBooks() {
   publishedBookRepository.saveAll(List.of(
       new PublishedBook(), new PublishedBook(), new PublishedBook(), new PublishedBook()
   ));

    var publishedBooks = publishedBookRepository.findAll();

    Assertions.assertThat(publishedBooks)
        .as("Published books should not be null or empty")
        .isNotEmpty()
        .isNotNull()
        .hasSize(4);
  }

  @Test
  void findById_shouldReturnPublishedBook() {
    publishedBookRepository.save(new PublishedBook());
    var bookId = publishedBookRepository.findAll().getFirst().getPublishedBookId();

    var publishedBook = publishedBookRepository.findById(bookId).get();

    Assertions.assertThat(publishedBook)
        .as("Published book should not be null")
        .isNotNull()
        .extracting(PublishedBook::getPublishedBookId).isEqualTo(bookId);
  }

  @Test
  void save_givenPublishedBook_shouldSavePublishedBook() {
    publishedBookRepository.save(new PublishedBook());

    Assertions.assertThat(publishedBookRepository.findAll())
        .as("Published book should not be null or empty")
        .isNotNull()
        .isNotEmpty()
        .hasSize(1);
  }

  @Test
  void deleteById_shouldDeletePublishedBook() {
    publishedBookRepository.saveAll(List.of(
        new PublishedBook(), new PublishedBook(), new PublishedBook(), new PublishedBook()
    ));
    var bookId = publishedBookRepository.findAll().getFirst().getPublishedBookId();

    publishedBookRepository.deleteById(bookId);

    Assertions.assertThat(publishedBookRepository.findAll())
        .as("One published book should be deleted from database")
        .hasSize(3); //4 before removal
  }
}
