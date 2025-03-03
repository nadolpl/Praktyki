package pl.sensilabs.praktyki.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
