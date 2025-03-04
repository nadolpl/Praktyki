package pl.sensilabs.praktyki.mappers;

import org.junit.jupiter.api.Test;
import pl.sensilabs.praktyki.entities.Publisher;
import pl.sensilabs.praktyki.responses.PublisherResponse;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PublisherMapperTest {

    @Test
    void toResponse_givenEntity_shouldMapToPublisherResponse() {

        // Given
        UUID publisherId = UUID.randomUUID();
        Publisher publisher = Publisher.builder()
                .publisherId(publisherId)
                .publisherName("Wydawnictwo Znak")
                .build();

        // When
        PublisherResponse response = PublisherMapper.toResponse(publisher);

        // Then
        assertThat(response)
                .isNotNull()
                .extracting(
                        PublisherResponse::publisherId,
                        PublisherResponse::publisherName
                )
                .containsExactly(publisher.getPublisherName());
    }
}
