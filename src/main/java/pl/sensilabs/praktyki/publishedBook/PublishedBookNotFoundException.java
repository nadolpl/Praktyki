package pl.sensilabs.praktyki.publishedBook;

import java.util.UUID;

class PublishedBookNotFoundException extends RuntimeException {
  PublishedBookNotFoundException(UUID publishedBookId) {
    super("Could not find published book with id " + publishedBookId);
  }
}
