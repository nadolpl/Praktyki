package pl.sensilabs.praktyki.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.entities.Book;
import pl.sensilabs.praktyki.entities.BookCategory;
import pl.sensilabs.praktyki.services.BookCategoryService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @GetMapping
        public ResponseEntity<List<BookCategory>> getTypes(){
        var response =  bookCategoryService.getTypes();
        log.info("Recived");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{category_id}")
        public  ResponseEntity<Void> deleteBookCategory(@PathVariable("category_id") int categoryId){
        bookCategoryService.deleteById(categoryId);
        log.info("Deleted category with id {}", categoryId);
        return ResponseEntity.noContent().build();
    }


}
