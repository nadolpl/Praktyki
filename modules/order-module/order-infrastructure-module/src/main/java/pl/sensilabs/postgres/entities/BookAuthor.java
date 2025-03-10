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
    @Column(name = "author_id", nullable = false)
    private UUID authorId;
    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    public BookAuthor(UUID authorId, UUID bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }
}
