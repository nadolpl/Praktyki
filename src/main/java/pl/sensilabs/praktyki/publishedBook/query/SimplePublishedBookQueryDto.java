package pl.sensilabs.praktyki.publishedBook.query;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "published_book")
public class SimplePublishedBookQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "published_book_id")
    private UUID id;

    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "publisher_id")
    private UUID publisherId;

    @Column(name = "book_type_id")
    private Integer bookTypeId;

    public SimplePublishedBookQueryDto(UUID id, UUID bookId, UUID publisherId, Integer bookTypeId) {
        this.id = id;
        this.bookId = bookId;
        this.publisherId = publisherId;
        this.bookTypeId = bookTypeId;
    }
}
