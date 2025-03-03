package pl.sensilabs.praktyki.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "published_book")
public class PublishedBook {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID publishedBookId;
  private UUID bookId;
  private UUID publisherId;
  private Integer bookTypeId;
  private Integer releaseNumber;
  private Instant publishDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "book_type_id")
  private BookType bookType;
}
