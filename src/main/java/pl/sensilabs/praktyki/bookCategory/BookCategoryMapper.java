package pl.sensilabs.praktyki.bookCategory;

import jakarta.validation.constraints.NotNull;

interface BookCategoryMapper {
    static BookCategoryResponse toResponse(@NotNull BookCategory bookCategory)
    {
        return new BookCategoryResponse(bookCategory.getId(), bookCategory.getName());
    }


    static BookCategory toEntity(@NotNull BookCategoryRequest bookCategoryRequest)
    {
        return new BookCategory(bookCategoryRequest.name());
    }
}

