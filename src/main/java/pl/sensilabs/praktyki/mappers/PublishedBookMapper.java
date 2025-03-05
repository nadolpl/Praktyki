package pl.sensilabs.praktyki.mappers;

import java.util.stream.Collectors;
import pl.sensilabs.praktyki.entities.PublishedBook;
import pl.sensilabs.praktyki.requests.PublishedBookRequest;
import pl.sensilabs.praktyki.responses.PublishedBookResponse;

public interface PublishedBookMapper {

  static PublishedBookResponse toResponse(PublishedBook publishedBook) {
    if (publishedBook == null) {
      return null;
    }

    return PublishedBookResponse.builder()
        .publishedBookId(publishedBook.getPublishedBookId())
        .bookTitle(publishedBook.getBook().getBookTitle())
        .authors(publishedBook.getBook().getAuthors().stream()
            .map(a -> a.getFirstName() + " " + a.getLastName())
            .collect(Collectors.toSet()))
        .publisherName(publishedBook.getPublisher().getName())
        .bookTypeName(publishedBook.getBookType().getBookTypeName())
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
