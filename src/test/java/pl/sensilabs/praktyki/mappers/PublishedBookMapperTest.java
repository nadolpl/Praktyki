package pl.sensilabs.praktyki.mappers;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.entities.PublishedBook;
import pl.sensilabs.praktyki.entities.Publisher;
import pl.sensilabs.praktyki.requests.PublishedBookRequest;

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
    book.setAuthors(new HashSet<>(List.of(author1, author2)));

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

    assertThat(response)
        .as("Response should not be null")
        .isNotNull();
    assertThat(response.bookTitle()).isEqualTo(entity.getBook().getBookTitle());
    assertThat(response.publisherName()).isEqualTo(entity.getPublisher().getPublisherName());
    assertThat(response.bookTypeName()).isEqualTo(entity.getBookType().getBookTypeName());
    assertThat(response.authors())
        .containsExactlyInAnyOrder("Andrzej Sapkowski", "Adam Walter");
  }

  @Test
  void toEntity_givenRequest_shouldMapToPublishedBookEntity() {
    var request =
        new PublishedBookRequest(UUID.randomUUID(), UUID.randomUUID(), 1, 2, LocalDate.now());

    var entity = PublishedBookMapper.toEntity(request);

    assertThat(entity)
        .as("Response should not be null")
        .isNotNull();
    assertThat(entity.getPublisherId()).isEqualTo(request.publisherId());
    assertThat(entity.getBookId()).isEqualTo(request.bookId());
    assertThat(entity.getBookTypeId()).isEqualTo(request.bookTypeId());
    assertThat(entity.getReleaseNumber()).isEqualTo(request.releaseNumber());
    assertThat(entity.getPublishDate()).isEqualTo(request.publishDate());
  }
}
