package pl.sensilabs.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID bookId;
  private String bookTitle;
  private Integer pages;
  private BigDecimal price;

  @ManyToMany(mappedBy = "books", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private Set<Author> authors;

  Book(String bookTitle, Integer pages) {
    this.bookTitle = bookTitle;
    this.pages = pages;
  }
}
