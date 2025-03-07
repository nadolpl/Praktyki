package pl.sensilabs.praktyki.publishedBook;

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

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/published-books")
class PublishedBookController {

  private final PublishedBookFacade publishedBookFacade;

  @GetMapping
  ResponseEntity<Iterable<PublishedBookResponse>> getAllPublishedBooks() {
    log.info("Received all published books GET request");
    var publishedBooks = publishedBookFacade.getAllPublishedBooks();
    return ResponseEntity.ok(publishedBooks);
  }

  @GetMapping("/{publishedBookId}")
  ResponseEntity<PublishedBookResponse> getPublishedBookById(
      @PathVariable UUID publishedBookId) {
    log.info("Received published book GET request with id {}", publishedBookId);
    var publishedBook = publishedBookFacade.getPublishedBookById(publishedBookId);
    return ResponseEntity.ok(publishedBook);
  }

  @PostMapping
  ResponseEntity<PublishedBookResponse> createPublishedBook(
      @Valid @RequestBody PublishedBookRequest request) {
    log.info("Received published book POST request with body {}", request);
    var publishedBook = publishedBookFacade.createPublishedBook(request);
    var location = URI.create("/api/published-books/" + publishedBook.publishedBookId());
    return ResponseEntity.created(location).body(publishedBook);
  }

  @PutMapping("/{publishedBookId}")
  ResponseEntity<PublishedBookResponse> updatePublishedBook(
      @PathVariable UUID publishedBookId,
      @Valid @RequestBody PublishedBookRequest request) {
    log.info("Received published book PUT request with id {} and body {}", publishedBookId, request);
    var publishedBook = publishedBookFacade.updatePublishedBook(publishedBookId, request);
    return ResponseEntity.ok(publishedBook);
  }

  @DeleteMapping("/{publishedBookId}")
  ResponseEntity<Void> deletePublishedBookById(@PathVariable UUID publishedBookId) {
    log.info("Received published book DELETE request with id {}", publishedBookId);
    publishedBookFacade.deletePublishedBookById(publishedBookId);
    return ResponseEntity.noContent().build();
  }
}
