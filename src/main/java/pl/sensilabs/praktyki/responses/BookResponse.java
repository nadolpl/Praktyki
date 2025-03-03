package pl.sensilabs.praktyki.responses;

import java.util.Set;
import java.util.UUID;
import lombok.Builder;

@Builder
public record BookResponse(
    UUID bookId,
    String bookTitle,
    int bookPages,
    String bookCategory,
    Set<String> authors) {

}
