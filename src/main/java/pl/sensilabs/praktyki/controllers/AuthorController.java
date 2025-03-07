package pl.sensilabs.praktyki.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.requests.AuthorRequest;
import pl.sensilabs.praktyki.responses.AuthorResponse;
import pl.sensilabs.praktyki.services.AuthorService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    ResponseEntity<Iterable<AuthorResponse>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{authorId}")
    ResponseEntity<AuthorResponse> findById(@PathVariable UUID authorId) {
        return ResponseEntity.ok(authorService.findById(authorId));
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable UUID authorId) {
        authorService.deleteById(authorId);
    }

    @PostMapping
    ResponseEntity<AuthorResponse> create(@RequestBody @Valid AuthorRequest authorRequest) {
        AuthorResponse author = authorService.create(authorRequest);
        URI location = URI.create("/api/authors/" + author.id());
        return ResponseEntity.created(location).body(author);
    }
}

