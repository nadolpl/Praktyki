package pl.sensilabs.praktyki.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="book_category")
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_name")
    private String categoryName;

    @Column(name="category_id")
    private int categoryId;

    @OneToMany
    @JoinColumn(name="category_id")
    private List<Book> BookCategoryBooks;


}
