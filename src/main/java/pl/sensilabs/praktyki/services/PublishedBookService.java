package pl.sensilabs.praktyki.services;

import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sensilabs.praktyki.exceptions.BookNotFoundException;
import pl.sensilabs.praktyki.exceptions.PublishedBookNotFoundException;
import pl.sensilabs.praktyki.exceptions.PublisherNotFoundException;
import pl.sensilabs.praktyki.exceptions.BookTypeNotFoundException;
import pl.sensilabs.praktyki.mappers.PublishedBookMapper;
import pl.sensilabs.praktyki.repositories.BookRepository;
import pl.sensilabs.praktyki.repositories.BookTypeRepository;
import pl.sensilabs.praktyki.repositories.PublishedBookRepository;
import pl.sensilabs.praktyki.repositories.PublisherRepository;
import pl.sensilabs.praktyki.requests.PublishedBookRequest;
import pl.sensilabs.praktyki.responses.PublishedBookResponse;

@Service
@AllArgsConstructor
public class PublishedBookService {

  private final PublishedBookRepository publishedBookRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;
  private final BookTypeRepository bookTypeRepository;

  public Iterable<PublishedBookResponse> getAllPublishedBooks() {
    return publishedBookRepository.findAll().stream()
        .map(PublishedBookMapper::toResponse)
        .collect(Collectors.toSet());
  }

  public PublishedBookResponse getPublishedBookById(UUID publishedBookId) {
    return publishedBookRepository.findById(publishedBookId)
        .map(PublishedBookMapper::toResponse)
        .orElseThrow(() -> new PublishedBookNotFoundException(publishedBookId));
  }

  @Transactional
  public PublishedBookResponse createPublishedBook(PublishedBookRequest publishedBookRequest) {
    validatePublishedBookRequest(publishedBookRequest);
    var entity = publishedBookRepository.save(
        PublishedBookMapper.toEntity(publishedBookRequest));
    return PublishedBookMapper.toResponse(entity);
  }

  @Transactional
  public PublishedBookResponse updatePublishedBook(
      UUID publishedBookId,
      PublishedBookRequest publishedBookRequest) {
    validatePublishedBookRequest(publishedBookRequest);
    var entity = publishedBookRepository.findById(publishedBookId)
        .orElseThrow(() -> new PublishedBookNotFoundException(publishedBookId));

    PublishedBookMapper.updateEntityFromRequest(entity, publishedBookRequest);
    return PublishedBookMapper.toResponse(entity);
  }

  private void validatePublishedBookRequest(PublishedBookRequest publishedBookRequest) {
    if (!bookRepository.existsById(publishedBookRequest.bookId())) {
      throw new BookNotFoundException(publishedBookRequest.bookId());
    }

    if (!publisherRepository.existsById(publishedBookRequest.publisherId())) {
      throw new PublisherNotFoundException(publishedBookRequest.publisherId());
    }

    if (!bookTypeRepository.existsById(publishedBookRequest.bookTypeId())) {
      throw new BookTypeNotFoundException(publishedBookRequest.bookTypeId());
    }
  }

  @Transactional
  public void deletePublishedBookById(UUID publishedBookId) {
    if (!publishedBookRepository.existsById(publishedBookId)) {
      throw new PublishedBookNotFoundException(publishedBookId);
    }

    publishedBookRepository.deleteById(publishedBookId);
  }
}
