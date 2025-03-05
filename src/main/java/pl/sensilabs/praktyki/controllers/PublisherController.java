package pl.sensilabs.praktyki.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.requests.PublisherRequest;
import pl.sensilabs.praktyki.responses.PublisherResponse;
import pl.sensilabs.praktyki.services.PublisherService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<Iterable<PublisherResponse>> findAll() {
        return ResponseEntity.ok(publisherService.findAll());
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<PublisherResponse> findById(@PathVariable UUID publisherId) {
        return ResponseEntity.ok(publisherService.findById(publisherId));
    }

    @DeleteMapping("/{publisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID publisherId) {
        publisherService.deleteById(publisherId);
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> create(@RequestBody @Valid PublisherRequest request) {
        PublisherResponse publisher = publisherService.create(request);
        URI location = URI.create("/api/publishers/" + publisher.id());
        return ResponseEntity.created(location).body(publisher);
    }
}
