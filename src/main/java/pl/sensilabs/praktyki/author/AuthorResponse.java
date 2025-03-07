package pl.sensilabs.praktyki.author;

import lombok.Builder;

import java.util.UUID;

@Builder
record AuthorResponse(UUID id, String firstName, String lastName, String email) {
}
