package pl.sensilabs.praktyki.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.repositories.AuthorRepository;
import pl.sensilabs.praktyki.responses.AuthorResponse;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void findAll_shouldReturnAllAuthors() {
        // Given
        UUID authorId = UUID.randomUUID();
        Author testAuthor = Author.builder()
                .authorId(authorId)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan@kowalski.com")
                .build();

        when(authorRepository.findAll()).thenReturn(List.of(testAuthor));

        // When
        Iterable<AuthorResponse> authors = authorService.findAll();

        // Then
        assertThat(authors)
                .hasSize(1)
                .extracting(
                        AuthorResponse::authorId,
                        AuthorResponse::firstName,
                        AuthorResponse::lastName,
                        AuthorResponse::email
                )
                .containsExactly(
                        tuple(authorId, "Jan", "Kowalski", "jan@kowalski.com")
                );

        verify(authorRepository).findAll();
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    void findById_shouldReturnAuthor() {
        // Given
        UUID authorId = UUID.randomUUID();
        Author testAuthor = Author.builder()
                .authorId(authorId)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan@kowalski.com")
                .build();

        when(authorRepository.findById(authorId)).thenReturn(java.util.Optional.of(testAuthor));

        // When
        AuthorResponse response = authorService.findById(authorId);

        // Then
        assertThat(response)
                .isNotNull()
                .extracting(
                    AuthorResponse::authorId,
                    AuthorResponse::firstName,
                    AuthorResponse::lastName,
                    AuthorResponse::email
                )
                .containsExactly(
                        authorId, "Jan", "Kowalski", "jan@kowalski.com"
                );

        verify(authorRepository).findById(authorId);
        verifyNoMoreInteractions(authorRepository);
    }

//    @Test
//    void save_shouldSaveAuthor(){
//        // Given
//        AuthorRequest testAuthor = AuthorRequest.builder()
//                .firstName("Jan")
//                .lastName("Kowalski")
//                .email("jan@kowalski.com")
//                .build();
//
//        Author authorEntity = AuthorMapper.toEntity(testAuthor);
//
//        when(authorRepository.save(authorEntity)).thenReturn(authorEntity);
//
//        // When
//        AuthorResponse response = authorService.create(testAuthor);
//
//        // Then
//
//        assertThat(response)
//                .isNotNull()
//                .extracting(
//                        AuthorResponse::firstName,
//                        AuthorResponse::lastName,
//                        AuthorResponse::email
//                )
//                .containsExactly(
//                        "Jan", "Kowalski", "jan@kowalski.com"
//                );
//    }
}
