package pl.sensilabs.praktyki.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.requests.BookTypeRequest;
import pl.sensilabs.praktyki.responses.BookTypeResponse;
import pl.sensilabs.praktyki.services.BookTypeService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/types")
class BookTypeController {

    private final BookTypeService bookTypeService;

    @GetMapping
    ResponseEntity<List<BookTypeResponse>> getTypes(){
        var response = bookTypeService.getTypes();
        log.info("Recived  book type response: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{typeId}")
    ResponseEntity<BookTypeResponse> getBookType(@PathVariable int typeId){
        var response = bookTypeService.getTypeById(typeId);
        log.info("Recived  book type response: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addType")
    ResponseEntity<BookTypeResponse> addType(@Valid @RequestBody BookTypeRequest bookTypeRequest){
        var response = bookTypeService.addType(bookTypeRequest);
        log.info("Type added : {}", bookTypeRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteType(@PathVariable int id){
        bookTypeService.deleteTypeById(id);
        log.info("Type deleted : {}", id);
        return ResponseEntity.noContent().build();
    }
}
