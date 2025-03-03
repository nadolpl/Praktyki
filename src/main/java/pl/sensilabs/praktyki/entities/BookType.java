package pl.sensilabs.praktyki.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="book_type")
public class BookType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_type_id")
    private Integer bookTypeId;

    private String bookTypeName;

    @OneToMany(mappedBy = "bookType")
    private List<PublishedBook> publishedBooks;
}
