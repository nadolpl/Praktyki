package pl.sensilabs.praktyki.publishedBook;

import java.util.stream.Collectors;

interface PublishedBookMapper {

  static PublishedBookResponse toResponse(PublishedBook publishedBook) {
    if (publishedBook == null) {
      return null;
    }

    return PublishedBookResponse.builder()
        .publishedBookId(publishedBook.getPublishedBookId())
        .bookTitle(publishedBook.getBook().getTitle())
        .authors(publishedBook.getBook().getAuthors().stream()
            .map(a -> a.getFirstName() + " " + a.getLastName())
            .collect(Collectors.toSet()))
        .publisherName(publishedBook.getPublisher().getName())
        .bookTypeName(publishedBook.getBookType().getName())
        .releaseNumber(publishedBook.getReleaseNumber())
        .publishDate(publishedBook.getPublishDate())
        .build();
  }

  static PublishedBook toEntity(PublishedBookRequest publishedBookRequest) {
    if (publishedBookRequest == null) {
      return null;
    }

    var entity = new PublishedBook();
    updateEntityFromRequest(entity, publishedBookRequest);
    return entity;
  }

  static void updateEntityFromRequest(PublishedBook entity, PublishedBookRequest request) {
    entity.setBookId(request.bookId());
    entity.setPublisherId(request.publisherId());
    entity.setBookTypeId(request.bookTypeId());
    entity.setReleaseNumber(request.releaseNumber());
    entity.setPublishDate(request.publishDate());
  }
}
