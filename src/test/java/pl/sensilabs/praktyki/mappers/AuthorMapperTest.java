package pl.sensilabs.praktyki.mappers;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.requests.AuthorRequest;
import pl.sensilabs.praktyki.responses.AuthorResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorMapperTest {

    private final UUID AUTHOR_ID = UUID.randomUUID();
    private final Set<Book> BOOKS = new HashSet<>(List.of(new Book(), new Book()));
    private final String FIRST_NAME = "Andrzej";
    private final String LAST_NAME = "Sapkowski";
    private final String EMAIL = "and.sapkowski@gmail.com";

    @Test
    void toResponse_givenEntity_shouldMapToAuthorResponse() {
        // given
        Author author = Author.builder()
                .id(AUTHOR_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .books(BOOKS)
                .build();

        // when
        AuthorResponse response = AuthorMapper.toResponse(author);

        // then
        assertThat(response)
                .isNotNull()
                .extracting(
                        AuthorResponse::id,
                        AuthorResponse::firstName,
                        AuthorResponse::lastName,
                        AuthorResponse::email
                )
                .containsExactly(
                        AUTHOR_ID,
                        FIRST_NAME,
                        LAST_NAME,
                        EMAIL
                );
    }

    @Test
    void toEntity_givenResponse_shouldMapToAuthor() {
        // given
        AuthorRequest authorRequest = new AuthorRequest(FIRST_NAME, LAST_NAME, EMAIL);

        // when
        Author author = AuthorMapper.toEntity(authorRequest);

        // then
        assertThat(author)
                .isNotNull()
                .extracting(
                        Author::getFirstName,
                        Author::getLastName,
                        Author::getEmail
                )
                .containsExactly(FIRST_NAME, LAST_NAME, EMAIL);

    }
}
