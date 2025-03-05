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
import pl.sensilabs.praktyki.requests.PublishedBookRequest;
import pl.sensilabs.praktyki.responses.PublishedBookResponse;
import pl.sensilabs.praktyki.services.PublishedBookService;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/published-books")
public class PublishedBookController {

  private final PublishedBookService publishedBookService;

  @GetMapping
  public ResponseEntity<Iterable<PublishedBookResponse>> getAllPublishedBooks() {
    log.info("Received all published books GET request");
    var publishedBooks = publishedBookService.getAllPublishedBooks();
    return ResponseEntity.ok(publishedBooks);
  }

  @GetMapping("/{publishedBookId}")
  public ResponseEntity<PublishedBookResponse> getPublishedBookById(
      @PathVariable UUID publishedBookId) {
    log.info("Received published book GET request with id {}", publishedBookId);
    var publishedBook = publishedBookService.getPublishedBookById(publishedBookId);
    return ResponseEntity.ok(publishedBook);
  }

  @PostMapping
  public ResponseEntity<PublishedBookResponse> createPublishedBook(
      @Valid @RequestBody PublishedBookRequest request) {
    log.info("Received published book POST request with body {}", request);
    var publishedBook = publishedBookService.createPublishedBook(request);
    var location = URI.create("/api/published-books/" + publishedBook.publishedBookId());
    return ResponseEntity.created(location).body(publishedBook);
  }

  @PutMapping("/{publishedBookId}")
  public ResponseEntity<PublishedBookResponse> updatePublishedBook(
      @PathVariable UUID publishedBookId,
      @Valid @RequestBody PublishedBookRequest request) {
    log.info("Received published book PUT request with id {} and body {}", publishedBookId, request);
    var publishedBook = publishedBookService.updatePublishedBook(publishedBookId, request);
    return ResponseEntity.ok(publishedBook);
  }

  @DeleteMapping("/{publishedBookId}")
  public ResponseEntity<Void> deletePublishedBookById(@PathVariable UUID publishedBookId) {
    log.info("Received published book DELETE request with id {}", publishedBookId);
    publishedBookService.deletePublishedBookById(publishedBookId);
    return ResponseEntity.noContent().build();
  }
}
