package pl.sensilabs.praktyki.services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.exceptions.*;
import pl.sensilabs.praktyki.mappers.BookTypeMapper;
import pl.sensilabs.praktyki.repositories.BookTypeRepository;
import pl.sensilabs.praktyki.requests.BookTypeRequest;
import pl.sensilabs.praktyki.responses.BookTypeResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookTypeService {

    private final BookTypeRepository bookTypeRepository;

    public List<BookTypeResponse> getTypes(){
        return bookTypeRepository.findAll().
                stream().
                map(BookTypeMapper::toResponse).
                collect(Collectors.toList());
    }

    public BookTypeResponse getTypeById(int id){
        return bookTypeRepository.findById(id).
                map(BookTypeMapper::toResponse).
                orElseThrow(()->new BookTypeIdNotFoundReqquestExceotion("Can not find type with this id"));
    }

    public void deleteTypeById(int id){
        if(!bookTypeRepository.existsById(id)){
            throw new BookTypeDeleteRequestException("Can not find type with this id");
        }
        bookTypeRepository.deleteById(id);
    }

    @Transactional
    public BookTypeResponse addType(BookTypeRequest bookTypeRequest){

        var types = bookTypeRequest.bookTypeName();
        if(bookTypeRepository.existsByBookTypeName(bookTypeRequest.bookTypeName())) {
            throw new IllegalArgumentException("Book type already exists");
        }
        var type = bookTypeRepository.save(BookTypeMapper.toEntity(bookTypeRequest));
        return BookTypeMapper.toResponse(type);
    }

}
