package pl.sensilabs.praktyki.responses;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PublishedBookResponse(
    UUID publishedBookId,
    String bookTitle,
    Set<String> authors,
    String publisherName,
    String bookTypeName,
    Integer releaseNumber,
    LocalDate publishDate) {

}
