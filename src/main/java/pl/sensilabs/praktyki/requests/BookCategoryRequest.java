package pl.sensilabs.praktyki.requests;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record BookCategoryRequest(
        @NotNull
        @Length(min=3,max=30)
        String categoryName) {
}
