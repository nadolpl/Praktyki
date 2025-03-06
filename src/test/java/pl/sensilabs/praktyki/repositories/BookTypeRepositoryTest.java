package pl.sensilabs.praktyki.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.entities.PublishedBook;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class BookTypeRepositoryTest {
    BookType bookType;
    @Autowired
    private BookTypeRepository bookTypeRepository;

    @BeforeEach
    void setup() {
        bookType = new BookType();
        bookType.setBookTypeName("test");
        bookType = bookTypeRepository.save(bookType); // ✅ Teraz Hibernate śledzi encję
    }

    @AfterEach
    public void delete(){
        bookTypeRepository.deleteAll();
    }

    @Test
    public void ShouldFindAllBookTypes()
    {
        bookTypeRepository.saveAll(List.of(
                new BookType(),
                new BookType()
        ));

        var  result = bookTypeRepository.findAll();

        assertThat(result)
                .isNotNull()
                .hasSize(3);
    }

}
