package pl.sensilabs.praktyki.exceptions;

import java.util.UUID;

public class PublishedBookNotFoundException extends RuntimeException {
  public PublishedBookNotFoundException(UUID publishedBookId) {
    super("Could not find published book with id " + publishedBookId);
  }
}
