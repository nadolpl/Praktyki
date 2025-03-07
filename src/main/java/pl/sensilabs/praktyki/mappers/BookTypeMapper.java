package pl.sensilabs.praktyki.mappers;

import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.requests.BookTypeRequest;
import pl.sensilabs.praktyki.responses.BookTypeResponse;

public interface BookTypeMapper {
    static BookTypeResponse toResponse(BookType bookType)
    {
        if(bookType==null) return BookTypeResponse.builder().build();

        return BookTypeResponse.builder().
                bookTypeName(bookType.getBookTypeName()).
                bookTypeId(bookType.getBookTypeId()).
                build();
    }


    static BookType toEntity(BookTypeRequest  bookTypeRequest)
    {
        if(bookTypeRequest==null) return null;
        BookType bookType = new BookType();
        bookType.setBookTypeName(bookTypeRequest.bookTypeName());
        return bookType;
    }
}
