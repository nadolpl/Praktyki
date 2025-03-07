package pl.sensilabs.praktyki.publisher;

interface PublisherMapper {
    static PublisherResponse toResponse(Publisher publisher) {
        return publisher == null ? PublisherResponse.builder().build() :
                PublisherResponse.builder()
                        .id(publisher.getId())
                        .name(publisher.getName())
                        .build();
    }

    static Publisher toEntity(PublisherRequest request) {
        return request == null ? new Publisher() :
                new Publisher(request.name());
    }
}
