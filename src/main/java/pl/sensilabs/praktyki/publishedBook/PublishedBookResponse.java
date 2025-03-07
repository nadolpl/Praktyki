package pl.sensilabs.praktyki.publishedBook;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
record PublishedBookResponse(
    UUID publishedBookId,
    String bookTitle,
    Set<String> authors,
    String publisherName,
    String bookTypeName,
    Integer releaseNumber,
    LocalDate publishDate
) { }
