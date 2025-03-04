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

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    @Test
    void findAll_shouldReturnAllPublishers(){
        // Given
        UUID publisherId = UUID.randomUUID();
        Publisher testPublisher = Publisher.builder()
                .publisherId(publisherId)
                .publisherName("Helion")
                .build();

        when(publisherRepository.findAll()).thenReturn(List.of(testPublisher));

        // When
        Iterable<PublisherResponse> publishers = publisherService.findAll();

        // Then
        assertThat(publishers)
                .hasSize(1)
                .extracting(
                        PublisherResponse::publisherId,
                        PublisherResponse::publisherName
                )
                .containsExactly(
                        tuple(publisherId, "Helion")
                );

        verify(publisherRepository).findAll();
        verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    void findById_shouldReturnPublisher() {
        // Given
        UUID publisherId = UUID.randomUUID();
        Publisher testPublisher = Publisher.builder()
                .publisherId(publisherId)
                .publisherName("Helion")
                .build();

        when(publisherRepository.findById(publisherId)).thenReturn(Optional.of(testPublisher));

        // When
        PublisherResponse publisher = publisherService.findById(publisherId);

        // Then
        assertThat(publisher)
                .isNotNull()
                .extracting(
                        PublisherResponse::publisherId,
                        PublisherResponse::publisherName
                )
                .containsExactly(
                        publisherId, "Helion"
                );
        verify(publisherRepository).findById(publisherId);
        verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    void findById_shouldThrowExceptionWhenPublisherNotFound() {
        // Given
        UUID publisherId = UUID.randomUUID();
        when(publisherRepository.findById(publisherId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> publisherService.findById(publisherId))
                .isInstanceOf(PublisherNotFoundException.class)
                .hasMessageContaining(publisherId.toString());

        verify(publisherRepository).findById(publisherId);
        verifyNoMoreInteractions(publisherRepository);
    }
}
