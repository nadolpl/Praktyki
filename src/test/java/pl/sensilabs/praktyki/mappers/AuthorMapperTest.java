package pl.sensilabs.praktyki.mappers;

import org.junit.jupiter.api.Test;
import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.responses.AuthorResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorMapperTest {

    @Test
    void toResponse_givenEntity_shouldMapToAuthorResponse() {
        // given
        UUID authorId = UUID.randomUUID();
        Set<Book> books = new HashSet<>(List.of(new Book(), new Book()));

        Author author = Author.builder()
                .authorId(authorId)
                .firstName("Andrzej")
                .lastName("Sapkowski")
                .email("and.sapkowski@gmail.com")
                .books(books)
                .build();

        // when
        AuthorResponse response = AuthorMapper.toResponse(author);

        // then
        assertThat(response)
                .isNotNull()
                .extracting(
                        AuthorResponse::authorId,
                        AuthorResponse::firstName,
                        AuthorResponse::lastName,
                        AuthorResponse::email
                )
                .containsExactly(
                        authorId,
                        author.getFirstName(),
                        author.getLastName(),
                        author.getEmail()
                );
    }
}
