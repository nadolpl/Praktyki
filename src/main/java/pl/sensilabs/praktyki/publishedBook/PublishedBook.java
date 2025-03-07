package pl.sensilabs.praktyki.publishedBook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sensilabs.praktyki.book.query.SimpleBookQueryDto;
import pl.sensilabs.praktyki.bookType.BookType;
import pl.sensilabs.praktyki.publishedBook.query.SimplePublishedBookQueryDto;
import pl.sensilabs.praktyki.publisher.Publisher;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "published_book")
class PublishedBook {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "published_book_id")
  private UUID publishedBookId;

  @Column(name = "book_id")
  private UUID bookId;

  @Column(name = "publisher_id")
  private UUID publisherId;

  @Column(name = "book_type_id")
  private Integer bookTypeId;

  private Integer releaseNumber;
  private LocalDate publishDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "book_id", insertable = false, updatable = false)
  private SimpleBookQueryDto book;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
  private Publisher publisher;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "book_type_id", insertable = false, updatable = false)
  private BookType bookType;
}
