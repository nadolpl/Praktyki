package pl.sensilabs.praktyki.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AuthorResponse(UUID id, String firstName, String lastName, String email) {
}
