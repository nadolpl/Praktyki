package pl.sensilabs.praktyki.author;

import jakarta.validation.constraints.NotNull;

interface AuthorMapper {
    static AuthorResponse toResponse(@NotNull Author author) {
        return AuthorResponse.builder()
            .id(author.getId())
            .firstName(author.getFirstName())
            .lastName(author.getLastName())
            .email(author.getEmail())
            .build();
    }

    static Author toEntity(@NotNull AuthorRequest request) {
        return new Author(
            request.firstName(),
            request.lastName(),
            request.email());
    }
}