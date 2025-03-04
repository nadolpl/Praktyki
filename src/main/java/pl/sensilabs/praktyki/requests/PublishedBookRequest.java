package pl.sensilabs.praktyki.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

public record PublishedBookRequest(
    @NotNull
    @org.hibernate.validator.constraints.UUID
    UUID bookId,

    @NotNull
    @org.hibernate.validator.constraints.UUID
    UUID publisherId,

    @NotNull
    @Size(min = 1, max = 3)
    Integer bookTypeId,

    @NotNull
    @Size(min = 1)
    Integer releaseNumber,

    @PastOrPresent
    LocalDate publishDate
) {

}
