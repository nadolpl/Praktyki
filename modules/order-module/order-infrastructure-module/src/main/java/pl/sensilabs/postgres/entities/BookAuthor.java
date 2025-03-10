package pl.sensilabs.postgres.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "book_author")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookAuthorId;

    private UUID authorId;

    private UUID bookId;

    BookAuthor(UUID authorId, UUID bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }
}
