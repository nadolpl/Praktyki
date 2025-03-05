package pl.sensilabs.praktyki.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.BookCategory;
import pl.sensilabs.praktyki.exceptions.CategoryNotFound;
import pl.sensilabs.praktyki.repositories.BookCategoryRepository;

import java.util.List;


@AllArgsConstructor
@Service
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    public List<BookCategory> getTypes(){
        return bookCategoryRepository.findAll();
    }

    public BookCategory getTypeById(int id){
        return bookCategoryRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id){
        if(!bookCategoryRepository.existsById(id)){
            throw new CategoryNotFound(id);
        }
        bookCategoryRepository.deleteById(id);
    }
}
