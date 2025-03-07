package pl.sensilabs.praktyki.book;

import jakarta.validation.constraints.NotNull;

import java.util.stream.Collectors;

interface BookMapper {

  static BookResponse toResponse(@NotNull Book book) {

    return BookResponse.builder()
        .id(book.getId())
        .title(book.getTitle())
        .pages(book.getPages())
        .category(book.getCategory().getName())
        .authors(book.getAuthors().stream()
            .map(a -> a.getFirstName() + ' ' + a.getLastName())
            .collect(Collectors.toSet()))
        .build();
  }

  static Book toEntity(@NotNull BookRequest request) {
    Book entity = new Book();
    updateEntityFromRequest(entity, request);
    return entity;
  }

  static void updateEntityFromRequest(Book entity, BookRequest request) {
    entity.setTitle(request.bookTitle());
    entity.setPages(request.pages());
    entity.setCategoryId(request.categoryId());
  }
}
