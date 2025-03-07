package pl.sensilabs.praktyki.controllers;

import jakarta.validation.Valid;
import java.net.URI;
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
class BookController {

  private final BookService bookService;

  @GetMapping
  ResponseEntity<Iterable<BookResponse>> getAllBooks() {
    log.info("Received all books GET request");
    var response = bookService.getAllBooks();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{bookId}")
  ResponseEntity<BookResponse> getBookById(@PathVariable UUID bookId) {
    log.info("Received a book GET request with id {}", bookId);
    var book = bookService.getBookById(bookId);
    return ResponseEntity.ok(book);
  }

  @PostMapping
  ResponseEntity<BookResponse> createBook(
      @Valid @RequestBody BookRequest bookRequest) {
    log.info("Received a book POST request with body: {}", bookRequest);
    var book = bookService.createBook(bookRequest);
    var location = URI.create("/api/books/" + book.bookId());
    return ResponseEntity.created(location).body(book);
  }

  @PutMapping("/{bookId}")
  ResponseEntity<BookResponse> updateBook(
      @PathVariable UUID bookId,
      @Valid @RequestBody BookRequest bookRequest) {
    log.info("Received a book PUT request with id {} and body {}", bookId, bookRequest);
    var book = bookService.updateBook(bookId, bookRequest);
    return ResponseEntity.ok(book);
  }

  @DeleteMapping("/{bookId}")
  ResponseEntity<Void> deleteBookById(@PathVariable UUID bookId) {
    log.info("Received a book DELETE request with id {}", bookId);
    bookService.deleteBookById(bookId);
    return ResponseEntity.noContent().build();
  }
}
