package pl.sensilabs.praktyki.bookCategory;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

record BookCategoryRequest(
        @NotNull
        @Length(min = 3, max = 30)
        String name) {
}
