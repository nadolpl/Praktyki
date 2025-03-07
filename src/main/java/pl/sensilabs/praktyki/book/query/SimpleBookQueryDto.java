package pl.sensilabs.praktyki.book.query;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.sensilabs.praktyki.author.query.SimpleAuthorQueryDto;

import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class SimpleBookQueryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_id")
    private UUID id;

    @Column(name = "book_title")
    private String title;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<SimpleAuthorQueryDto> authors;
}
