package pl.sensilabs.praktyki.mappers;

import org.junit.jupiter.api.Test;
import pl.sensilabs.praktyki.entities.Publisher;
import pl.sensilabs.praktyki.requests.PublisherRequest;
import pl.sensilabs.praktyki.responses.PublisherResponse;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PublisherMapperTest {

    private final UUID PUBLISHER_ID = UUID.randomUUID();
    private final String PUBLISHER_NAME = "Helion";

    @Test
    void toResponse_givenEntity_shouldMapToPublisherResponse() {

        // Given
        Publisher publisher = new Publisher(PUBLISHER_NAME);
        publisher.setId(PUBLISHER_ID);

        // When
        PublisherResponse response = PublisherMapper.toResponse(publisher);

        // Then
        assertThat(response)
                .isNotNull()
                .as("Publisher should be returned")
                .extracting(
                        PublisherResponse::id,
                        PublisherResponse::name
                )
                .containsExactly(
                        publisher.getId(),
                        publisher.getName()
                );
    }

    @Test
    void toEntity_givenRequest_shouldMapToPublisherEntity() {
        // Given
        PublisherRequest request = new PublisherRequest(PUBLISHER_NAME);

        // When
        Publisher publisher = PublisherMapper.toEntity(request);
        publisher.setId(PUBLISHER_ID);

        // Then
        assertThat(publisher)
                .isNotNull()
                .as("Publisher should be returned")
                .extracting(
                        Publisher::getId,
                        Publisher::getName
                )
                .containsExactly(
                        PUBLISHER_ID,
                        PUBLISHER_NAME
                );
    }
}
