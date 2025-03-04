package pl.sensilabs.praktyki.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.BookCategory;
import pl.sensilabs.praktyki.exceptions.CategoryNameExist;
import pl.sensilabs.praktyki.exceptions.CategoryNotFound;
import pl.sensilabs.praktyki.mappers.BookCategoryMapper;
import pl.sensilabs.praktyki.mappers.BookTypeMapper;
import pl.sensilabs.praktyki.repositories.BookCategoryRepository;
import pl.sensilabs.praktyki.responses.BookCategoryResponse;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    public List<BookCategoryResponse> getTypes(){

        return bookCategoryRepository.findAll().stream().map(BookCategoryMapper::toResponse).collect(Collectors.toList());

    }


    public void deleteById(int id){
        if(!bookCategoryRepository.existsById(id)){
            throw new CategoryNotFound(id);
        }
        bookCategoryRepository.deleteById(id);
    }

    public BookCategory addBookCategory(BookCategory bookCategory){
        if(bookCategoryRepository.existsBookCategoryByCategoryName(bookCategory.getCategoryName())){
            throw new CategoryNameExist(bookCategory.getCategoryName());
        }
        return bookCategoryRepository.save(bookCategory);
    }
}
