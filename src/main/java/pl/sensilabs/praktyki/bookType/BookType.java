package pl.sensilabs.praktyki.bookType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "book_type")
public class BookType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_type_id")
    private Integer id;

    @Column(name = "book_type_name")
    private String name;

    BookType(String name) {
        this.name = name;
    }
}
