package pl.sensilabs.praktyki.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sensilabs.praktyki.entities.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    Author author;

    @BeforeEach
    void setUp() {
        author = new Author("Miłosz", "Nadolski", "miloszgaming@gmail.com");
    }

    @AfterEach
    public void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void findAll_shouldReturnAllAuthors() {
        // Given
        authorRepository.saveAll(List.of(
           new Author("Adam", "Małysz", "Niemałysz@skok.pl"),
            new Author("Dawid", "Miłek", "bezkręgowce@bbc.com"),
            new Author("Stanisław", "Wszelak", "lypa@warunek.pl")
        ));

        // When
        var authors = authorRepository.findAll();

        // Then
        assertThat(authors)
                .as("Authors should not be null or empty")
                .isNotEmpty()
                .hasSize(3);
    }

    @Test
    void findById_shouldReturnAuthor() {

        // Given
        Author savedAuthor = authorRepository.save(author);
        var authorId = savedAuthor.getId();

        // When
        var foundAuthor = authorRepository.findById(authorId).orElse(null);

        // Then
        assertThat(foundAuthor)
                .as("Found author should not be null")
                .isNotNull()
                .isEqualTo(savedAuthor);
    }

    @Test
    void save_givenAuthor_shouldSaveAuthor() {
        // When
        Author savedAuthor = authorRepository.save(author);

        // Then
        assertThat(savedAuthor)
                .as("Saved author should have an ID")
                .extracting(Author::getId)
                .isNotNull();

        assertThat(authorRepository.findById(savedAuthor.getId()))
                .as("Author should be retrievable from database")
                .isPresent()
                .hasValueSatisfying(found -> assertThat(found).isEqualTo(savedAuthor));
    }

    @Test
    void deleteById_shouldDeleteAuthor() {

        // Given
        Author savedAuthor = authorRepository.save(author);
        var authorId = savedAuthor.getId();

        assertThat(authorRepository.findById(authorId))
                .as("Author should be retrievable from database before deletion")
                .isPresent();

        // When
        authorRepository.deleteById(authorId);

        // Then
        assertThat(authorRepository.findById(authorId))
                .as("Author should not exist after deletion")
                .isEmpty();
    }
}
