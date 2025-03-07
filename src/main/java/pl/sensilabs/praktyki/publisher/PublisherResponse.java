package pl.sensilabs.praktyki.publisher;

import lombok.Builder;

import java.util.UUID;

@Builder
record PublisherResponse (UUID id, String name) { }
