package pl.sensilabs.praktyki.mappers;

import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.responses.AuthorResponse;

public interface AuthorMapper {
    static AuthorResponse toResponse(Author author) {
        return author == null ? AuthorResponse.builder().build() :
                AuthorResponse.builder()
                        .authorId(author.getAuthorId())
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .email(author.getEmail())
                        .build();
    }
}