package pl.sensilabs.praktyki.bookCategory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookCategoryFacade {

    private final BookCategoryRepository bookCategoryRepository;

    List<BookCategoryResponse> getTypes(){
        return bookCategoryRepository.findAll().
                stream().
                map(BookCategoryMapper::toResponse).
                collect(Collectors.toList());
    }

    public boolean bookCategoryExistsById(Integer id) {
        return bookCategoryRepository.existsById(id);
    }

    @Transactional
    BookCategoryResponse addBookCategory(BookCategoryRequest bookCategoryRequest){
        if(bookCategoryRepository.existsBookCategoryByName(bookCategoryRequest.name()))
        {
            throw  new IllegalArgumentException("BookCategory already exists");
        };
        var category = bookCategoryRepository.save(BookCategoryMapper.toEntity(bookCategoryRequest));
        return BookCategoryMapper.toResponse(category);
    }

    void deleteById(int id){
        if(!bookCategoryRepository.existsById(id)){
            throw new BookCategoryNotFoundException(id);
        }
        bookCategoryRepository.deleteById(id);
    }
}
