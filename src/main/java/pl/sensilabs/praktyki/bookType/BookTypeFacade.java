package pl.sensilabs.praktyki.bookType;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookTypeFacade {

    private final BookTypeRepository bookTypeRepository;

    List<BookTypeResponse> getTypes(){
        return bookTypeRepository.findAll().
                stream().
                map(BookTypeMapper::toResponse).
                collect(Collectors.toList());
    }

    BookTypeResponse getTypeById(int id){
        return bookTypeRepository.findById(id).
                map(BookTypeMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("Book type not found"));
    }

    void deleteTypeById(int id){
        if(!bookTypeRepository.existsById(id)){
            throw new EntityNotFoundException("Book type not found");
        }
        bookTypeRepository.deleteById(id);
    }

    public boolean bookTypeExistsById(int id) {
        return bookTypeRepository.existsById(id);
    }

    @Transactional
    BookTypeResponse addType(BookTypeRequest bookTypeRequest){
        if(bookTypeRepository.existsByName(bookTypeRequest.name())) {
            throw new IllegalArgumentException("Book type already exists");
        }

        var type = bookTypeRepository.save(BookTypeMapper.toEntity(bookTypeRequest));
        return BookTypeMapper.toResponse(type);
    }
}
