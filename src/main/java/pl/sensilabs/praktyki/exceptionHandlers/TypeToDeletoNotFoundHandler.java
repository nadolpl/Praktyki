package pl.sensilabs.praktyki.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.sensilabs.praktyki.exceptions.BookTypeDeleteException;
import pl.sensilabs.praktyki.exceptions.BookTypeDeleteRequestException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class TypeToDeletoNotFoundHandler {
    @ExceptionHandler(BookTypeDeleteRequestException.class)
    public ResponseEntity<Object> handler(BookTypeDeleteRequestException e) {
        BookTypeDeleteException bookTypeDeleteException = new BookTypeDeleteException(
                e.getMessage(),
                HttpStatus.NO_CONTENT,
                ZonedDateTime.now(ZoneId.of("Europe/Paris"))
        );
        return new ResponseEntity<>(bookTypeDeleteException, HttpStatus.NOT_FOUND);
    }
}
