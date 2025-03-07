package pl.sensilabs.praktyki.book;

import java.util.Set;
import java.util.UUID;
import lombok.Builder;

@Builder
record BookResponse(
    UUID id,
    String title,
    int pages,
    String category,
    Set<String> authors) {
}
