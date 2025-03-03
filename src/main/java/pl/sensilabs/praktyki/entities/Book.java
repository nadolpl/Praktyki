package pl.sensilabs.praktyki.entities;

import jakarta.persistence.*;

import java.util.Set;
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
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID bookId;
  private String bookTitle;
  private Integer pages;
  private Integer categoryId;

  @OneToMany(mappedBy = "book")
  private Set<BookAuthor> bookAuthors;
}
