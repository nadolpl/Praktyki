package pl.sensilabs.praktyki.responses;

import java.time.Instant;
import java.util.Set;
import lombok.Builder;

@Builder
public record PublishedBookResponse(
    String bookTitle,
    Set<String> authors,
    String publisherName,
    String bookTypeName,
    int releaseNumber,
    Instant publishDate) {

}
