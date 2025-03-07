package pl.sensilabs.praktyki.author.query;

import jakarta.persistence.*;
import lombok.*;
import pl.sensilabs.praktyki.book.query.SimpleBookQueryDto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "author")
public class SimpleAuthorQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "author_id")
    private UUID id;

    private String firstName;

    private String lastName;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<SimpleBookQueryDto> books = new HashSet<>();
}