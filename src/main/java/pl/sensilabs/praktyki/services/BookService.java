package pl.sensilabs.praktyki.services;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.exceptions.BookNotFoundException;
import pl.sensilabs.praktyki.mappers.BookMapper;
import pl.sensilabs.praktyki.repositories.BookRepository;
import pl.sensilabs.praktyki.responses.BookResponse;

@Service
@AllArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public Set<BookResponse> getAllBooks() {
    return bookRepository.findAll().stream()
        .map(BookMapper::toResponse)
        .collect(Collectors.toSet());
  }

  public BookResponse getBookById(UUID bookId) {
    return bookRepository.findById(bookId)
        .map(BookMapper::toResponse)
        .orElseThrow(() -> new BookNotFoundException(bookId)
        );
  }

  public void deleteBookById(UUID bookId) {
    if (!bookRepository.existsById(bookId)) {
      throw new BookNotFoundException(bookId);
    }

    bookRepository.deleteById(bookId);
  }
}
