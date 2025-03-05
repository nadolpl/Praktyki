package pl.sensilabs.praktyki.mappers;

import java.util.stream.Collectors;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.requests.BookRequest;
import pl.sensilabs.praktyki.responses.BookResponse;

public interface BookMapper {

  static BookResponse toResponse(Book book) {
    if (book == null) {
      return BookResponse.builder().build();
    }

    return BookResponse.builder()
        .bookId(book.getBookId())
        .bookTitle(book.getBookTitle())
        .bookPages(book.getPages())
        .bookCategory(book.getBookCategory().getCategoryName())
        .authors(book.getAuthors().stream()
            .map(a -> a.getFirstName() + a.getLastName())
            .collect(Collectors.toSet()))
        .build();
  }

  static Book toEntity(BookRequest request) {
    if (request == null) {
      return null;
    }

    Book entity = new Book();
    updateEntityFromRequest(entity, request);
    return entity;
  }

  static void updateEntityFromRequest(Book entity, BookRequest request) {
    entity.setBookTitle(request.bookTitle());
    entity.setPages(request.pages());
    entity.setCategoryId(request.categoryId());
  }
}
