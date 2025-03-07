package pl.sensilabs.praktyki.bookType;

import jakarta.validation.constraints.NotNull;

interface BookTypeMapper {
    static BookTypeResponse toResponse(@NotNull BookType bookType)
    {
        return new BookTypeResponse(bookType.getId(), bookType.getName());
    }


    static BookType toEntity(@NotNull BookTypeRequest request)
    {
        return new BookType(request.name());
    }
}
