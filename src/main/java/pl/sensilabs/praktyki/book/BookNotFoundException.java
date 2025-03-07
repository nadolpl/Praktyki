package pl.sensilabs.praktyki.book;

import java.util.UUID;

class BookNotFoundException extends RuntimeException {
  BookNotFoundException(UUID bookId) {
    super("Could not find book with id " + bookId);
  }
}
