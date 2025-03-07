package pl.sensilabs.praktyki.bookType;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

record BookTypeRequest (
    @NotNull
    @Length(min = 3, max = 20)
    String name
) {}
