package pl.sensilabs.praktyki.publisher;

import java.util.UUID;

class PublisherNotFoundException extends RuntimeException {
    PublisherNotFoundException(UUID id) {
        super("Could not find publisher with id " + id);
    }
}
