package pl.sensilabs.praktyki.requests;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AuthorIdRequest(
    @NotNull
    @org.hibernate.validator.constraints.UUID
    UUID id
) {

}
