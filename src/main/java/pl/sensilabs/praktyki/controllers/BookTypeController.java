package pl.sensilabs.praktyki.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.entities.BookType;
import pl.sensilabs.praktyki.responses.BookTypeResponse;
import pl.sensilabs.praktyki.services.BookTypeService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/types")
public class BookTypeController {

    private final BookTypeService bookTypeService;

    @GetMapping
    public List<BookType> getTypes(){
        return bookTypeService.getTypes();
    }

    @PostMapping("/addType")
    public ResponseEntity<BookType> addType(@RequestBody BookType bookType){
        var response = bookTypeService.addType(bookType);
        log.info("Type added : {}", bookType);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable int id){
        bookTypeService.deleteTypeById(id);
        log.info("Type deleted : {}", id);
        return ResponseEntity.noContent().build();
    }
}
