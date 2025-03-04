package pl.sensilabs.praktyki.mappers;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.entities.PublishedBook;
import pl.sensilabs.praktyki.entities.Publisher;

class PublishedBookMapperTest {
  @Test
  void toResponse_givenEntity_shouldMapToPublishedBookResponse() {
    var book = new Book();
    book.setBookTitle("Hobbit");

    var author1 = new Author();
    author1.setFirstName("Andrzej");
    author1.setLastName("Sapkowski");

    var author2 = new Author();
    author2.setFirstName("Adam");
    author2.setLastName("Walter");
    book.setAuthors(new HashSet<Author>(List.of(author1, author2)));

    var publisher = new Publisher();
    publisher.setPublisherName("Helion");

    var bookType = new BookType();
    bookType.setBookTypeName("E-Book");

    var entity = new PublishedBook();
    entity.setBookTypeId(123);
    entity.setPublisher(publisher);
    entity.setBook(book);
    entity.setBookType(bookType);
    entity.setReleaseNumber(7);
    entity.setPublishDate(LocalDate.now());

    var response = PublishedBookMapper.toResponse(entity);

    Assertions.assertThat(response)
        .as("Response should not be null")
        .isNotNull();
    //do zrobienia assertions porównujące wartości
  }
}
