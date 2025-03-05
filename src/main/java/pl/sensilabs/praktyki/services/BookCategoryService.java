package pl.sensilabs.praktyki.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.BookCategory;
import pl.sensilabs.praktyki.exceptions.BookCategoryNotFoundException;
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

    public  BookCategory addBookCategory(BookCategory bookCategory){
        if(bookCategoryRepository.existsBookCategoryByCategoryName(bookCategory.getCategoryName()))
        {
            throw  new IllegalArgumentException("BookCategory already exists");
        };
        return bookCategoryRepository.save(bookCategory);
    }

    public void deleteById(int id){
        if(!bookCategoryRepository.existsById(id)){
            throw new BookCategoryNotFoundException(id);
        }
        bookCategoryRepository.deleteById(id);
    }
}
