package pl.sensilabs.praktyki.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_author")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookAuthorId;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
