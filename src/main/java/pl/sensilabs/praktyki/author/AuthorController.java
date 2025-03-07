package pl.sensilabs.praktyki.author;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
class AuthorController {

    private final AuthorFacade authorFacade;

    @GetMapping
    ResponseEntity<Iterable<AuthorResponse>> findAll() {
        return ResponseEntity.ok(authorFacade.findAll());
    }

    @GetMapping("/{authorId}")
    ResponseEntity<AuthorResponse> findById(@PathVariable UUID authorId) {
        return ResponseEntity.ok(authorFacade.findById(authorId));
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable UUID authorId) {
        authorFacade.deleteById(authorId);
    }

    @PostMapping
    ResponseEntity<AuthorResponse> create(@RequestBody @Valid AuthorRequest authorRequest) {
        AuthorResponse author = authorFacade.create(authorRequest);
        URI location = URI.create("/" + author.id());
        return ResponseEntity.created(location).body(author);
    }
}

