package pl.sensilabs.praktyki.bookType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/types")
class BookTypeController {

    private final BookTypeFacade bookTypeService;

    @GetMapping
    ResponseEntity<List<BookTypeResponse>> getTypes(){
        var response = bookTypeService.getTypes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{typeId}")
    ResponseEntity<BookTypeResponse> getBookType(@PathVariable int typeId){
        var response = bookTypeService.getTypeById(typeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addType")
    ResponseEntity<BookTypeResponse> addType(@Valid @RequestBody BookTypeRequest bookTypeRequest){
        var response = bookTypeService.addType(bookTypeRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteType(@PathVariable int id){
        bookTypeService.deleteTypeById(id);
        return ResponseEntity.noContent().build();
    }
}
