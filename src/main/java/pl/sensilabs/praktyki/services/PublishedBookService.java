package pl.sensilabs.praktyki.services;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.exceptions.PublishedBookNotFoundException;
import pl.sensilabs.praktyki.mappers.PublishedBookMapper;
import pl.sensilabs.praktyki.repositories.PublishedBookRepository;
import pl.sensilabs.praktyki.responses.PublishedBookResponse;

@Service
@AllArgsConstructor
public class PublishedBookService {

  private final PublishedBookRepository publishedBookRepository;

  public Set<PublishedBookResponse> getAllPublishedBooks() {
    return publishedBookRepository.findAll().stream()
        .map(PublishedBookMapper::toResponse)
        .collect(Collectors.toSet());
  }

  public PublishedBookResponse getPublishedBookById(UUID publishedBookId) {
    return publishedBookRepository.findById(publishedBookId)
        .map(PublishedBookMapper::toResponse)
        .orElseThrow(() -> new PublishedBookNotFoundException(publishedBookId)
        );
  }

  public void deletePublishedBookById(UUID publishedBookId) {
    if(!publishedBookRepository.existsById(publishedBookId)) {
      throw new PublishedBookNotFoundException(publishedBookId);
    }

    publishedBookRepository.deleteById(publishedBookId);
  }
}
