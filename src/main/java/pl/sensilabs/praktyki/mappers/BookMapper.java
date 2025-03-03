package pl.sensilabs.praktyki.mappers;

import java.util.stream.Collectors;
import pl.sensilabs.praktyki.entities.Book;
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
        .bookCategory(book.getCategory().getCategoryName())
        .authors(book.getAuthors().stream()
            .map(a -> a.getFirstName() + a.getLastName())
            .collect(Collectors.toSet()))
        .build();
  }
}
