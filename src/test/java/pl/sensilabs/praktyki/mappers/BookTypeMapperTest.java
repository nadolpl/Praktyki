package pl.sensilabs.praktyki.mappers;


import org.junit.jupiter.api.Test;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.entities.PublishedBook;
import pl.sensilabs.praktyki.responses.BookTypeResponse;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BookTypeMapperTest {
    @Test
    void mapperTest (){
        BookType booktype = new BookType( 1, "test", List.of(new PublishedBook(), new PublishedBook(), new PublishedBook()));

        BookTypeResponse response = BookTypeMapper.toResponse(booktype);

        assertThat(response)
                .isNotNull()
                .extracting(
                        BookTypeResponse::bookTypeId,
                        BookTypeResponse::bookTypeName
                )
                .containsExactly(
                        1,
                        "test"

                );
    }
}
