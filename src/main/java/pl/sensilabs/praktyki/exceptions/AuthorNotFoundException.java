package pl.sensilabs.praktyki.exceptions;

import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(UUID id) {
        super("Could not find author with id " + id);
    }
}
