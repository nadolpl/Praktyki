package pl.sensilabs.praktyki.mappers;

import pl.sensilabs.praktyki.entities.Publisher;
import pl.sensilabs.praktyki.requests.PublisherRequest;
import pl.sensilabs.praktyki.responses.PublisherResponse;

public interface PublisherMapper {
    static PublisherResponse toResponse(Publisher publisher) {
        return publisher == null ? PublisherResponse.builder().build() :
                PublisherResponse.builder()
                        .id(publisher.getId())
                        .name(publisher.getName())
                        .build();
    }

    static Publisher toEntity(PublisherRequest request) {
        return request == null ? Publisher.builder().build() :
                Publisher.builder()
                        .name(request.name())
                        .build();
    }
}
