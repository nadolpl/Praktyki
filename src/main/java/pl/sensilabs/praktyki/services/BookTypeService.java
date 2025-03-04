package pl.sensilabs.praktyki.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.exceptions.BookTypeNotFoundException;
import pl.sensilabs.praktyki.repositories.BookTypeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookTypeService {

    private final BookTypeRepository bookTypeRepository;

    public List<BookType> getTypes(){
        return bookTypeRepository.findAll();
    }

    public BookType getTypeById(int id){
        return bookTypeRepository.findById(id).orElseThrow();
    }

    public void deleteTypeById(int id){
        if(!bookTypeRepository.existsById(id)){
            throw new BookTypeNotFoundException(id);
        }
        bookTypeRepository.deleteById(id);
    }

    public BookType addType(BookType bookType){
      return   bookTypeRepository.save(bookType);
    }

}
