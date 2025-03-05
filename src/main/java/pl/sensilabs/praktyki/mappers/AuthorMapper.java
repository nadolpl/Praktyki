package pl.sensilabs.praktyki.mappers;

import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.requests.AuthorRequest;
import pl.sensilabs.praktyki.responses.AuthorResponse;

public interface AuthorMapper {
    static AuthorResponse toResponse(Author author) {
        return author == null ? AuthorResponse.builder().build() :
                AuthorResponse.builder()
                        .id(author.getId())
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .email(author.getEmail())
                        .build();
    }

    static Author toEntity(AuthorRequest request) {
        if(request == null) return null;
        return new Author(
            request.firstName(),
            request.lastName(),
            request.email());
    }
}