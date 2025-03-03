package pl.sensilabs.praktyki.mappers;

import pl.sensilabs.praktyki.entities.BookCategory;
import pl.sensilabs.praktyki.responses.BookCategoryResponse;

public interface BookCategoryMapper {
    static BookCategoryResponse toResponse(BookCategory bookCategory)
    {
        if(bookCategory == null) return BookCategoryResponse.builder().build();

        return BookCategoryResponse.builder().categoryName(bookCategory.getCategoryName()).build();
    }
}

