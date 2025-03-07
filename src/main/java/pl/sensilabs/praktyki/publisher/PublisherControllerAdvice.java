package pl.sensilabs.praktyki.publisher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class PublisherControllerAdvice {

    @ExceptionHandler(PublisherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ProblemDetail publisherNotFoundHandler(PublisherNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
