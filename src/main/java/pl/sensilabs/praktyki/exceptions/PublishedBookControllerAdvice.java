package pl.sensilabs.praktyki.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PublishedBookControllerAdvice {
  @ExceptionHandler(PublishedBookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> publishedBookNotFoundHandler(PublishedBookNotFoundException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
