package pl.sensilabs.praktyki.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.exceptions.TypeNotFound;
import pl.sensilabs.praktyki.mappers.BookTypeMapper;
import pl.sensilabs.praktyki.repositories.BookTypeRepository;
import pl.sensilabs.praktyki.responses.BookTypeResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookTypeService {

    private final BookTypeRepository bookTypeRepository;

    public Set<BookTypeResponse> getTypes(){

        return bookTypeRepository.findAll().stream().map(BookTypeMapper::toResponse).collect(Collectors.toSet());
    }


    public void deleteTypeById(int id){
        if(!bookTypeRepository.existsById(id)){
            throw new TypeNotFound(id);
        }
        bookTypeRepository.deleteById(id);
    }

    public BookType addType(BookType bookType){
      return   bookTypeRepository.save(bookType);
    }

}
