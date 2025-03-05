package pl.sensilabs.praktyki.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sensilabs.praktyki.entities.Book;

@DataJpaTest
class BookRepositoryTest {

  @Autowired
  BookRepository bookRepository;

  @AfterEach
  public void tearDown() {
    bookRepository.deleteAll();
  }

  @Test
  void findAll_shouldReturnAllBooks() {
    bookRepository.saveAll(List.of(new Book(), new Book(), new Book()));

    var expectedBooks = bookRepository.findAll();

    assertThat(expectedBooks)
        .as("Books should not be null or empty")
        .isNotNull()
        .isNotEmpty();
  }
}
