package pl.sensilabs.praktyki.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sensilabs.praktyki.entities.BookCategory;
import pl.sensilabs.praktyki.services.BookCategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/API")
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @GetMapping("/test2")
            public List<BookCategory> getTypes(){
        return bookCategoryService.getTypes();
    }
}
