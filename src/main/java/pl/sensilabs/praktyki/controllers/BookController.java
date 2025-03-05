package pl.sensilabs.praktyki.controllers;

import jakarta.validation.Valid;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sensilabs.praktyki.requests.BookRequest;
import pl.sensilabs.praktyki.responses.BookResponse;
import pl.sensilabs.praktyki.services.BookService;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  @GetMapping
  public ResponseEntity<Set<BookResponse>> getAllBooks() {
    var response = bookService.getAllBooks();
    log.info("Received all books GET request");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<BookResponse> getBookById(@PathVariable("bookId") UUID bookId) {
    var book = bookService.getBookById(bookId);
    log.info("Received a book GET request with id {}", bookId);
    return ResponseEntity.ok(book);
  }

  @PostMapping
  public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
    var book = bookService.createBook(bookRequest);
    log.info("Received a book POST request");
    return ResponseEntity.ok(book);
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<BookResponse> updateBook(
      @PathVariable("bookId") UUID bookId,
      @Valid @RequestBody BookRequest bookRequest) {
    var book = bookService.updateBook(bookId, bookRequest);
    log.info("Received a book PUT request with id {}", bookId);
    return ResponseEntity.ok(book);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<Void> deleteBookById(@PathVariable("bookId") UUID bookId) {
    log.info("Received a book DELETE request with id {}", bookId);
    bookService.deleteBookById(bookId);
    return ResponseEntity.noContent().build();
  }
}
