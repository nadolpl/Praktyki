package pl.sensilabs.praktyki.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.requests.AuthorRequest;
import pl.sensilabs.praktyki.responses.AuthorResponse;
import pl.sensilabs.praktyki.services.AuthorService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<Iterable<AuthorResponse>> findAll() {
        Iterable<AuthorResponse> authors = authorService.findAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("{authorId}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable UUID authorId) {
        AuthorResponse author = authorService.findById(authorId);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID authorId) {
        authorService.deleteById(authorId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{authorId}")
    public ResponseEntity<AuthorResponse> create(@RequestBody AuthorRequest authorRequest) {
        AuthorResponse author = authorService.create(authorRequest);
        return ResponseEntity.created(URI.create("/api/author/" + author.authorId())).body(author);
    }
}

