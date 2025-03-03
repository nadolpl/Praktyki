package pl.sensilabs.praktyki.requests;

import lombok.Builder;

@Builder
public record AuthorRequest(String firstName, String lastName, String email) {
}
