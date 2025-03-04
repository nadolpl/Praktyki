package pl.sensilabs.praktyki.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sensilabs.praktyki.responses.PublishedBookResponse;
import pl.sensilabs.praktyki.services.PublishedBookService;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/published-books")
public class PublishedBookController {

  private final PublishedBookService publishedBookService;

  @GetMapping
  public ResponseEntity<List<PublishedBookResponse>> getAllPublishedBooks() {
    var publishedBooks = publishedBookService.getAllPublishedBooks();
    log.info("Received all published books GET request");
    return ResponseEntity.ok(publishedBooks);
  }

  @GetMapping("/{publishedBookId}")
  public ResponseEntity<PublishedBookResponse> getPublishedBookById(
      @PathVariable UUID publishedBookId) {
    var publishedBook = publishedBookService.getPublishedBookById(publishedBookId);
    log.info("Received published book GET request with id {}", publishedBookId);
    return ResponseEntity.ok(publishedBook);
  }

  @DeleteMapping("/{publishedBookId}")
  public ResponseEntity<Void> deletePublishedBookById(@PathVariable UUID publishedBookId) {
    log.info("Received published book DELETE request with id {}", publishedBookId);
    publishedBookService.deletePublishedBookById(publishedBookId);
    return ResponseEntity.noContent().build();
  }
}
