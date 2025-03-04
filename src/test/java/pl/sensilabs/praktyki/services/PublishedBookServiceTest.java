package pl.sensilabs.praktyki.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
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
import pl.sensilabs.praktyki.exceptions.PublishedBookNotFoundException;
import pl.sensilabs.praktyki.repositories.BookRepository;
import pl.sensilabs.praktyki.repositories.BookTypeRepository;
import pl.sensilabs.praktyki.repositories.PublishedBookRepository;
import pl.sensilabs.praktyki.repositories.PublisherRepository;
import pl.sensilabs.praktyki.requests.PublishedBookRequest;

@ExtendWith(MockitoExtension.class)
class PublishedBookServiceTest {

  @Mock
  PublishedBookRepository publishedBookRepository;

  @Mock
  BookRepository bookRepository;

  @Mock
  PublisherRepository publisherRepository;

  @Mock
  BookTypeRepository bookTypeRepository;

  @InjectMocks
  PublishedBookService publishedBookService;

  List<PublishedBook> publishedBooks;

  @BeforeEach
  void setUp() {
    var book = new Book();
    book.setBookTitle("Book title");
    book.setBookId(UUID.randomUUID());

    var author = new Author();
    author.setFirstName("Adam");
    author.setLastName("Mickiewicz");
    author.setAuthorId(UUID.randomUUID());
    book.setAuthors(new HashSet<>(List.of(author)));

    var publisher = new Publisher();
    publisher.setPublisherName("PWN");
    publisher.setPublisherId(UUID.randomUUID());

    var bookType = new BookType();
    bookType.setBookTypeId(3);
    bookType.setBookTypeName("E-Book");

    publishedBooks = List.of(
        new PublishedBook(), new PublishedBook(), new PublishedBook());
    publishedBooks.forEach(pb -> {
      pb.setPublishedBookId(UUID.randomUUID());
      pb.setBook(book);
      pb.setBookId(book.getBookId());
      pb.setPublisher(publisher);
      pb.setPublisherId(publisher.getPublisherId());
      pb.setPublishDate(LocalDate.now());
      pb.setBookType(bookType);
      pb.setBookTypeId(bookType.getBookTypeId());
      pb.setReleaseNumber(1);
      pb.setPublishDate(LocalDate.now().minusMonths(3));
    });
  }

  @Test
  void getAllPublishedBooks_shouldReturnAllPublishedBooks() {
    when(publishedBookRepository.findAll()).thenReturn(publishedBooks);

    var allPublishedBooks = publishedBookService.getAllPublishedBooks();

    verify(publishedBookRepository, times(1)).findAll();
    assertThat(allPublishedBooks)
        .as("Published books should not be null or empty")
        .isNotNull()
        .isNotEmpty()
        .hasSize(3);
  }

  @Test
  void getPublishedBookById_givenId_shouldReturnPublishedBook() {
    var publishedBook = publishedBooks.getFirst();
    when(publishedBookRepository.findById(publishedBook.getPublishedBookId()))
        .thenReturn(Optional.of(publishedBook));

    var expectedPublishedBook = publishedBookService
        .getPublishedBookById(publishedBook.getPublishedBookId());

    assertThat(expectedPublishedBook)
        .as("Published book should not be null")
        .isNotNull();
  }

  @Test
  void getPublishedBookById_givenWrongId_shouldReturnPublishedBook() {
    var publishedBookId = UUID.randomUUID();

    assertThatThrownBy(() -> publishedBookService.getPublishedBookById(publishedBookId))
        .isInstanceOf(PublishedBookNotFoundException.class)
        .hasMessageContaining("Could not find published book with id " + publishedBookId);
  }

  @Test
  void updatePublishedBook_givenIdAndRequest_shouldUpdatePublishedBook() {
    var publishedBook = publishedBooks.getFirst();

    var request = new PublishedBookRequest(
        publishedBook.getBookId(),
        publishedBook.getPublisherId(),
        1,
        3,
        LocalDate.now());

    when(publishedBookRepository.findById(publishedBook.getPublishedBookId()))
        .thenReturn(Optional.of(publishedBook));

    when(bookRepository.existsById(request.bookId())).thenReturn(true);
    when(bookTypeRepository.existsById(request.bookTypeId())).thenReturn(true);
    when(publisherRepository.existsById(request.publisherId())).thenReturn(true);

    var updatedPublishedBook = publishedBookService
        .updatePublishedBook(publishedBook.getPublishedBookId(), request);

    assertThat(updatedPublishedBook.releaseNumber()).isEqualTo(request.releaseNumber());
    assertThat(updatedPublishedBook.publishDate()).isEqualTo(request.publishDate());
  }
}
