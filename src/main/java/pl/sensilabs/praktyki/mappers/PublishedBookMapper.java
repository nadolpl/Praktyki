package pl.sensilabs.praktyki.mappers;

import java.util.stream.Collectors;
import pl.sensilabs.praktyki.entities.PublishedBook;
import pl.sensilabs.praktyki.requests.PublishedBookRequest;
import pl.sensilabs.praktyki.responses.PublishedBookResponse;

public interface PublishedBookMapper {

  static PublishedBookResponse toResponse(PublishedBook publishedBook) {
    if (publishedBook == null) {
      return PublishedBookResponse.builder().build();
    }

    return PublishedBookResponse.builder()
        .publishedBookId(publishedBook.getPublishedBookId())
        .bookTitle(publishedBook.getBook().getBookTitle())
        .authors(publishedBook.getBook().getAuthors().stream()
            .map(a -> a.getFirstName() + a.getLastName())
            .collect(Collectors.toSet()))
        .publisherName(publishedBook.getPublisher().getPublisherName())
        .bookTypeName(publishedBook.getBookType().getBookTypeName())
        .releaseNumber(publishedBook.getReleaseNumber())
        .publishDate(publishedBook.getPublishDate())
        .build();
  }

  static PublishedBook toEntity(PublishedBookRequest publishedBookRequest) {
    var entity = new PublishedBook();
    entity.setBookId(publishedBookRequest.getBookId());
    entity.setPublisherId(publishedBookRequest.getPublisherId());
    entity.setBookTypeId(publishedBookRequest.getBookTypeId());
    entity.setReleaseNumber(publishedBookRequest.getReleaseNumber());
    entity.setPublishDate(publishedBookRequest.getPublishDate());
    return entity;
  }
}
