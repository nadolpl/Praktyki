package pl.sensilabs.praktyki.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sensilabs.praktyki.exceptions.PublishedBookNotFoundException;

@RestControllerAdvice
public class PublishedBookControllerAdvice {

  @ExceptionHandler(PublishedBookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ProblemDetail> publishedBookNotFoundHandler(PublishedBookNotFoundException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problemDetail.setDetail(ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
  }
}
