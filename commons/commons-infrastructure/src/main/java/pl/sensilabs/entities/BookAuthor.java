package pl.sensilabs.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "book_author")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookAuthorId;

    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "book_id")
    private UUID bookId;

    BookAuthor(UUID authorId, UUID bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }
}
