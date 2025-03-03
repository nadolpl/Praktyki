package pl.sensilabs.praktyki.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.repositories.BookTypeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookTypeService {

    private final BookTypeRepository bookTypeRepository;

    public List<BookType> getTypes(){
        return bookTypeRepository.findAll();
    }

}
