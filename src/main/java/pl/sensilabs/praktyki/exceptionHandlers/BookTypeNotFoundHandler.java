package pl.sensilabs.praktyki.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sensilabs.praktyki.exceptions.BookTypeIdNotFoundException;
import pl.sensilabs.praktyki.exceptions.BookTypeIdNotFoundReqquestExceotion;


import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class BookTypeNotFoundHandler {

    @ExceptionHandler(value = {BookTypeIdNotFoundReqquestExceotion.class})
    public ResponseEntity<Object> handleException(BookTypeIdNotFoundReqquestExceotion e) {

        BookTypeIdNotFoundException bookTypeIdNotFoundException = new BookTypeIdNotFoundException(
                e.getMessage(),
                HttpStatus.NO_CONTENT,
                ZonedDateTime.now(ZoneId.of("Europe/Paris"))

        );
        return new ResponseEntity<>(bookTypeIdNotFoundException, HttpStatus.NOT_FOUND);
    }
}
