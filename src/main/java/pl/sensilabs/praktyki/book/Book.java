package pl.sensilabs.praktyki.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sensilabs.praktyki.author.query.SimpleAuthorQueryDto;
import pl.sensilabs.praktyki.bookCategory.BookCategory;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "book_id")
  private UUID id;

  @Column(name = "book_title")
  private String title;

  private Integer pages;

  @Column(name = "category_id", nullable = false)
  private Integer categoryId;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  private BookCategory category;

  @ManyToMany(mappedBy = "books", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private Set<SimpleAuthorQueryDto> authors;
}
