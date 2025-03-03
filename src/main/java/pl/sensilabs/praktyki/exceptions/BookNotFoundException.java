package pl.sensilabs.praktyki.exceptions;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(UUID bookId) {
    super("Could not find book with id " + bookId);
  }
}
