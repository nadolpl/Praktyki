package pl.sensilabs.praktyki.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sensilabs.praktyki.entities.Publisher;
import pl.sensilabs.praktyki.exceptions.PublisherNotFoundException;
import pl.sensilabs.praktyki.repositories.PublisherRepository;
import pl.sensilabs.praktyki.responses.PublisherResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherServiceTest {

    private final String PUBLISHER_NAME = "Helion";
    private final UUID PUBLISHER_ID = UUID.randomUUID();

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    @Test
    void findAll_shouldReturnAllPublishers(){
        // Given
        Publisher testPublisher = new Publisher(PUBLISHER_NAME);
        testPublisher.setId(PUBLISHER_ID);

        when(publisherRepository.findAll()).thenReturn(List.of(testPublisher));

        // When
        Iterable<PublisherResponse> publishers = publisherService.findAll();

        // Then
        assertThat(publishers)
                .hasSize(1)
                .extracting(
                        PublisherResponse::id,
                        PublisherResponse::name
                )
                .containsExactly(
                        tuple(PUBLISHER_ID, PUBLISHER_NAME)
                );

        verify(publisherRepository).findAll();
        verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    void findById_shouldReturnPublisher() {
        // Given
        Publisher testPublisher = new Publisher(PUBLISHER_NAME);
        testPublisher.setId(PUBLISHER_ID);

        when(publisherRepository.findById(PUBLISHER_ID)).thenReturn(Optional.of(testPublisher));

        // When
        PublisherResponse publisher = publisherService.findById(PUBLISHER_ID);

        // Then
        assertThat(publisher)
                .isNotNull()
                .extracting(
                        PublisherResponse::id,
                        PublisherResponse::name
                )
                .containsExactly(
                        PUBLISHER_ID, PUBLISHER_NAME
                );
        verify(publisherRepository).findById(PUBLISHER_ID);
        verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    void findById_shouldThrowExceptionWhenPublisherNotFound() {
        // Given
        when(publisherRepository.findById(PUBLISHER_ID)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> publisherService.findById(PUBLISHER_ID))
                .isInstanceOf(PublisherNotFoundException.class)
                .hasMessageContaining(PUBLISHER_ID.toString());

        verify(publisherRepository).findById(PUBLISHER_ID);
        verifyNoMoreInteractions(publisherRepository);
    }
}
