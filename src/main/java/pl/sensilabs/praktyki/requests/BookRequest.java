package pl.sensilabs.praktyki.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import org.hibernate.validator.constraints.Length;

public record BookRequest(
    @NotBlank
    @Length(min = 2, max = 127)
    String bookTitle,

    @NotNull
    @Size(min = 2)
    Integer pages,

    @NotNull
    @Length(min = 1)
    Integer categoryId,

    @NotEmpty
    @Size(min = 1)
    Set<AuthorIdRequest> authors
) {

}
