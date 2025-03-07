package pl.sensilabs.praktyki.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.BookCategory;
import pl.sensilabs.praktyki.exceptions.BookCategoryNotFoundException;
import pl.sensilabs.praktyki.mappers.BookCategoryMapper;
import pl.sensilabs.praktyki.repositories.BookCategoryRepository;
import pl.sensilabs.praktyki.requests.BookCategoryRequest;
import pl.sensilabs.praktyki.responses.BookCategoryResponse;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    public List<BookCategoryResponse> getTypes(){
        return bookCategoryRepository.findAll().
                stream().
                map(BookCategoryMapper::toResponse).
                collect(Collectors.toList());
    }

    public BookCategory getTypeById(int id){
        return bookCategoryRepository.findById(id).orElseThrow();
    }

    @Transactional
    public  BookCategoryResponse addBookCategory(BookCategoryRequest bookCategoryRequest){
        if(bookCategoryRepository.existsBookCategoryByCategoryName(bookCategoryRequest.categoryName()))
        {
            throw  new IllegalArgumentException("BookCategory already exists");
        };
        var category = bookCategoryRepository.save(BookCategoryMapper.toEntity(bookCategoryRequest));
        return BookCategoryMapper.toResponse(category);
    }

    public void deleteById(int id){
        if(!bookCategoryRepository.existsById(id)){
            throw new BookCategoryNotFoundException(id);
        }
        bookCategoryRepository.deleteById(id);
    }
}
