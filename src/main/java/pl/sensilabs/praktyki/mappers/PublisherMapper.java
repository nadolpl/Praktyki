package pl.sensilabs.praktyki.mappers;

import pl.sensilabs.praktyki.entities.Publisher;
import pl.sensilabs.praktyki.responses.PublisherResponse;

public interface PublisherMapper {
    static PublisherResponse toResponse(Publisher publisher) {
        return publisher == null ? PublisherResponse.builder().build() :
                PublisherResponse.builder()
                        .publisherId(publisher.getPublisherId())
                        .publisherName(publisher.getPublisherName())
                        .build();
    }
}
