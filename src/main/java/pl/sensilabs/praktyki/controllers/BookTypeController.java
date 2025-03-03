package pl.sensilabs.praktyki.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.services.BookTypeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class BookTypeController {

    private final BookTypeService bookTypeService;

    @GetMapping("/test")
    public List<BookType> getTypes(){
        return bookTypeService.getTypes();
    }

    @PostMapping("/addType")
    public BookType addType(@RequestBody BookType bookType){
        return bookTypeService.addType(bookType);
    }
}
