package pl.sensilabs.praktyki.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.BookCategory;
import pl.sensilabs.praktyki.repositories.BookCategoryRepository;

import java.util.List;


@AllArgsConstructor
@Service
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    public List<BookCategory> getTypes(){
        return bookCategoryRepository.findAll();
    }
}
