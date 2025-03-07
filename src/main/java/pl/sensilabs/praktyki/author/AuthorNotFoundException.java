package pl.sensilabs.praktyki.author;

import java.util.UUID;

class AuthorNotFoundException extends RuntimeException {
    AuthorNotFoundException(UUID id) {
        super("Could not find author with id " + id);
    }
}
