package pl.sensilabs.praktyki.exceptions;

import java.util.UUID;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(UUID id) {
        super("Could not find publisher with id " + id);
    }
}
