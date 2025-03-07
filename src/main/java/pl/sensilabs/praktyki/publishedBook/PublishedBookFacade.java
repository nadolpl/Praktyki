package pl.sensilabs.praktyki.publishedBook;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sensilabs.praktyki.book.BookFacade;
import pl.sensilabs.praktyki.bookType.BookTypeFacade;
import pl.sensilabs.praktyki.publisher.PublisherFacade;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublishedBookFacade {

  private final PublishedBookRepository publishedBookRepository;
  private final BookFacade bookFacade;
  private final PublisherFacade publisherFacade;
  private final BookTypeFacade bookTypeFacade;

  Iterable<PublishedBookResponse> getAllPublishedBooks() {
    return publishedBookRepository.findAll().stream()
        .map(PublishedBookMapper::toResponse)
        .collect(Collectors.toSet());
  }

  PublishedBookResponse getPublishedBookById(UUID publishedBookId) {
    return publishedBookRepository.findById(publishedBookId)
        .map(PublishedBookMapper::toResponse)
        .orElseThrow(() -> new PublishedBookNotFoundException(publishedBookId));
  }

  @Transactional
  PublishedBookResponse createPublishedBook(PublishedBookRequest publishedBookRequest) {
    validatePublishedBookRequest(publishedBookRequest);
    var entity = publishedBookRepository.save(
        PublishedBookMapper.toEntity(publishedBookRequest));
    return PublishedBookMapper.toResponse(entity);
  }

  @Transactional
  PublishedBookResponse updatePublishedBook(
      UUID publishedBookId,
      PublishedBookRequest publishedBookRequest) {
    validatePublishedBookRequest(publishedBookRequest);
    var entity = publishedBookRepository.findById(publishedBookId)
        .orElseThrow(() -> new PublishedBookNotFoundException(publishedBookId));

    PublishedBookMapper.updateEntityFromRequest(entity, publishedBookRequest);
    return PublishedBookMapper.toResponse(entity);
  }

  private void validatePublishedBookRequest(PublishedBookRequest request) {
    if (!bookFacade.bookExistsById(request.bookId())) {
      throw new EntityNotFoundException();
    }

    if (publisherFacade.publisherExistsById(request.publisherId())) {
      throw new EntityNotFoundException("Publisher not found with id: " + request.publisherId());
    }

    if (!bookTypeFacade.bookTypeExistsById(request.bookTypeId())) {
      throw new EntityNotFoundException();
    }
  }

  @Transactional
  void deletePublishedBookById(UUID publishedBookId) {
    if (!publishedBookRepository.existsById(publishedBookId)) {
      throw new PublishedBookNotFoundException(publishedBookId);
    }

    publishedBookRepository.deleteById(publishedBookId);
  }
}
