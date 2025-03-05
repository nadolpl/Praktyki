package pl.sensilabs.praktyki.services;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sensilabs.praktyki.entities.BookAuthor;
import pl.sensilabs.praktyki.exceptions.AuthorNotFoundException;
import pl.sensilabs.praktyki.exceptions.BookNotFoundException;
import pl.sensilabs.praktyki.exceptions.BookCategoryNotFoundException;
import pl.sensilabs.praktyki.mappers.BookMapper;
import pl.sensilabs.praktyki.repositories.AuthorRepository;
import pl.sensilabs.praktyki.repositories.AuthorRepository.AuthorIdOnly;
import pl.sensilabs.praktyki.repositories.BookAuthorRepository;
import pl.sensilabs.praktyki.repositories.BookCategoryRepository;
import pl.sensilabs.praktyki.repositories.BookRepository;
import pl.sensilabs.praktyki.requests.AuthorIdRequest;
import pl.sensilabs.praktyki.requests.BookRequest;
import pl.sensilabs.praktyki.responses.BookResponse;

@Service
@AllArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final BookAuthorRepository bookAuthorRepository;
  private final AuthorRepository authorRepository;
  private final BookCategoryRepository bookCategoryRepository;

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

  public BookResponse createBook(BookRequest bookRequest) {
    validateBookCategoryFromRequest(bookRequest.categoryId());
    var authorsIds = bookRequest.authors().stream()
        .map(AuthorIdRequest::id)
        .collect(Collectors.toSet());
    validateAuthorsFromRequest(authorsIds);

    var book = bookRepository.save(BookMapper.toEntity(bookRequest));

    for(var author : authorsIds) {
      bookAuthorRepository.save(new BookAuthor(null, author, book.getBookId()));
    }

    return BookMapper.toResponse(book);
  }

  @Transactional
  public BookResponse updateBook(UUID bookId, BookRequest bookRequest) {
    validateBookCategoryFromRequest(bookRequest.categoryId());
    var authorsIds = bookRequest.authors().stream()
        .map(AuthorIdRequest::id)
        .collect(Collectors.toSet());
    validateAuthorsFromRequest(authorsIds);

    var book = bookRepository.findById(bookId)
        .orElseThrow(() -> new BookNotFoundException(bookId));

    BookMapper.updateEntityFromRequest(book, bookRequest);
    bookAuthorRepository.deleteAllByBookId(book.getBookId());

    for(var author : authorsIds) {
      bookAuthorRepository.save(new BookAuthor(null, author, book.getBookId()));
    }

    return BookMapper.toResponse(book);
  }

  private void validateBookCategoryFromRequest(Integer categoryId) {
    if(!bookCategoryRepository.existsById(categoryId)) {
      throw new BookCategoryNotFoundException(categoryId);
    }
  }

  private void validateAuthorsFromRequest(Set<UUID> authors) {
    var allAuthors = authorRepository.findAllBy().stream()
        .map(AuthorIdOnly::getId).toList();
    for (var author : authors) {
      if (!allAuthors.contains(author)) {
        throw new AuthorNotFoundException(author);
      }
    }
  }

  public void deleteBookById(UUID bookId) {
    if (!bookRepository.existsById(bookId)) {
      throw new BookNotFoundException(bookId);
    }

    bookRepository.deleteById(bookId);
  }
}
