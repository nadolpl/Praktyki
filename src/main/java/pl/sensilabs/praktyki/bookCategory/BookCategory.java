package pl.sensilabs.praktyki.bookCategory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "book_category")
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Integer id;

    @Column(name="category_name")
    private String name;

    BookCategory(String name) {
        this.name = name;
    }
}
