package pl.sensilabs.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "author")
class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "author_id")
  private UUID id;

  private String firstName;

  private String lastName;

  private String email;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "book_author",
      joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  private Set<Book> books = new HashSet<>();

  Author(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
}