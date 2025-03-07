package pl.sensilabs.praktyki.bookCategory;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
class BookCategoryController {

    private final BookCategoryFacade bookCategoryFacade;

    @GetMapping
    ResponseEntity<List<BookCategoryResponse>> getTypes(){
        var response =  bookCategoryFacade.getTypes();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    ResponseEntity<BookCategoryResponse> addBookCategory(@Valid @RequestBody BookCategoryRequest bookCategoryRequest) {
        var response = bookCategoryFacade.addBookCategory(bookCategoryRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{category_id}")
    ResponseEntity<Void> deleteBookCategory(@PathVariable("category_id") int categoryId){
        bookCategoryFacade.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
