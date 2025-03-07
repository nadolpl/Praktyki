package pl.sensilabs.praktyki.bookAuthor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookAuthorFacade {

    private final BookAuthorRepository bookAuthorRepository;

    public void deleteAllByBookId(UUID id) {
        bookAuthorRepository.deleteAllByBookId(id);
    }

    public void save(BookAuthor bookAuthor) {
        bookAuthorRepository.save(bookAuthor);
    }
}
