package pl.sensilabs.praktyki.book;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sensilabs.praktyki.author.AuthorFacade;
import pl.sensilabs.praktyki.bookAuthor.BookAuthor;
import pl.sensilabs.praktyki.bookAuthor.BookAuthorFacade;
import pl.sensilabs.praktyki.bookCategory.BookCategoryFacade;
import pl.sensilabs.praktyki.author.AuthorIdRequest;

@Service
@RequiredArgsConstructor
public class BookFacade {

  private final BookRepository bookRepository;
  private final BookAuthorFacade bookAuthorFacade;
  private final AuthorFacade authorFacade;
  private final BookCategoryFacade bookCategoryFacade;

  Iterable<BookResponse> getAllBooks() {
    return bookRepository.findAll().stream()
        .map(BookMapper::toResponse)
        .collect(Collectors.toSet());
  }

  BookResponse getBookById(UUID bookId) {
    return bookRepository.findById(bookId)
        .map(BookMapper::toResponse)
        .orElseThrow(() -> new BookNotFoundException(bookId)
        );
  }

  public boolean bookExistsById(UUID id) {
    return bookRepository.existsById(id);
  }

  @Transactional
  BookResponse createBook(BookRequest bookRequest) {
    validateBookCategoryFromRequest(bookRequest.categoryId());
    var authorsIds = bookRequest.authors().stream()
        .map(AuthorIdRequest::id)
        .collect(Collectors.toSet());
    validateAuthorsFromRequest(authorsIds);

    var book = bookRepository.save(BookMapper.toEntity(bookRequest));

    for(var author : authorsIds) {
      bookAuthorFacade.save(new BookAuthor(author, book.getId()));
    }

    return BookMapper.toResponse(book);
  }

  @Transactional
  BookResponse updateBook(UUID bookId, BookRequest bookRequest) {
    validateBookCategoryFromRequest(bookRequest.categoryId());
    var authorsIds = bookRequest.authors().stream()
        .map(AuthorIdRequest::id)
        .collect(Collectors.toSet());
    validateAuthorsFromRequest(authorsIds);

    var book = bookRepository.findById(bookId)
        .orElseThrow(() -> new BookNotFoundException(bookId));

    BookMapper.updateEntityFromRequest(book, bookRequest);
    bookAuthorFacade.deleteAllByBookId(book.getId());

    for(var author : authorsIds) {
      bookAuthorFacade.save(new BookAuthor(author, book.getId()));
    }

    return BookMapper.toResponse(book);
  }

  private void validateBookCategoryFromRequest(Integer id) {
    if(!bookCategoryFacade.bookCategoryExistsById(id)) {
      throw new EntityNotFoundException();
    }
  }

  private void validateAuthorsFromRequest(Set<UUID> authors) {
    authorFacade.validateAuthorsFromRequest(authors);
  }

  @Transactional
  void deleteBookById(UUID bookId) {
    if (!bookRepository.existsById(bookId)) {
      throw new BookNotFoundException(bookId);
    }

    bookRepository.deleteById(bookId);
  }
}
