package pl.sensilabs.praktyki.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.entities.PublishedBook;
import pl.sensilabs.praktyki.entities.Publisher;
import pl.sensilabs.praktyki.repositories.PublishedBookRepository;

@ExtendWith(MockitoExtension.class)
class PublishedBookServiceTest {

  @Mock
  PublishedBookRepository publishedBookRepository;
  @InjectMocks
  PublishedBookService publishedBookService;

  @Test
  void getAllPublishedBooks_shouldReturnAllPublishedBooks() {
    //arrange
    var book = new Book();
    book.setBookTitle("Book title");
    var author = new Author();
    author.setFirstName("Adam");
    author.setLastName("Mickiewicz");
    book.setAuthors(new HashSet<>(List.of(author)));
    var publisher = new Publisher();
    publisher.setPublisherName("PWN");
    var bookType = new BookType();
    bookType.setBookTypeName("E-Book");
    var publishedBooks = List.of(
        new PublishedBook(), new PublishedBook(), new PublishedBook());
    publishedBooks.forEach(pb -> {
      pb.setBook(book);
      pb.setPublisher(publisher);
      pb.setPublishDate(LocalDate.now());
      pb.setBookType(bookType);
    });
    when(publishedBookRepository.findAll()).thenReturn(publishedBooks);

    //act
    var allPublishedBooks = publishedBookService.getAllPublishedBooks();

    //assert
    verify(publishedBookRepository, times(1)).findAll();
    Assertions.assertThat(allPublishedBooks)
        .as("Published books should not be null or empty")
        .isNotNull()
        .isNotEmpty()
        .hasSize(3);
  }
}
