package pl.sensilabs.praktyki.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sensilabs.praktyki.entities.Publisher;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository publisherRepository;

    @AfterEach
    public void tearDown() {
        publisherRepository.deleteAll();
    }

    @Test
    void findAll_shouldReturnAllPublishers() {
        // Given
        publisherRepository.saveAll(List.of(
                new Publisher("PWN"),
                new Publisher("Helion"),
                new Publisher("Tomek")
        ));

        // When
        var publishers = publisherRepository.findAll();

        // Then
        assertThat(publishers)
                .as("Publishers should not be null or empty")
                .isNotEmpty()
                .hasSize(3);
    }

    @Test
    void findById_shouldReturnPublisher() {

        // Given
        Publisher publisher = publisherRepository.save(new Publisher("Tomek"));
        var publisherId = publisher.getId();

        // When
        var foundPublisher = publisherRepository.findById(publisherId).orElse(null);

        // Then
        assertThat(foundPublisher)
                .as("Found publisher should not be null")
                .isNotNull()
                .isEqualTo(publisher);
    }

    @Test
    void save_givenPublisher_shouldSavePublisher() {

        // Given
        Publisher publisher = new Publisher("Tomek");

        // When
        Publisher savedPublisher = publisherRepository.save(publisher);

        // Then
        assertThat(savedPublisher)
                .as("Saved publisher should have an ID")
                .extracting(Publisher::getId)
                .isNotNull();

        assertThat(publisherRepository.findById(savedPublisher.getId()))
                .as("Publisher should be retrievable from database")
                .isPresent()
                .hasValueSatisfying(found -> assertThat(found).isEqualTo(savedPublisher));
    }

    @Test
    void deleteById_shouldDeletePublisher() {

        // Given
        Publisher publisher = publisherRepository.save(new Publisher("Tomek"));
        var publisherId = publisher.getId();

        assertThat(publisherRepository.findById(publisherId))
                .as("Publisher should be retrievable from database before deletion")
                .isPresent();

        // When
        publisherRepository.deleteById(publisherId);

        // Then
        assertThat(publisherRepository.findById(publisherId))
                .as("Publisher should not exist after deletion")
                .isEmpty();
    }
}
