package pl.sensilabs.praktyki.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.entities.Book;
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
    public ResponseEntity<List<BookTypeResponse>> getTypes(){
        var response = bookTypeService.getTypes();
        log.info("Recived  book type response: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{typeId}")
    public ResponseEntity<BookTypeResponse> getBookType(@PathVariable int typeId){
        var response = bookTypeService.getTypeById(typeId);
        log.info("Recived  book type response: {}", response);
        return ResponseEntity.ok(response);
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
