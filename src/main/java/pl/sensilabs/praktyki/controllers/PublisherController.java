package pl.sensilabs.praktyki.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.requests.PublisherRequest;
import pl.sensilabs.praktyki.responses.PublisherResponse;
import pl.sensilabs.praktyki.services.PublisherService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<Iterable<PublisherResponse>> findAll() {
        Iterable<PublisherResponse> publishers = publisherService.findAll();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("{publisherId}")
    public ResponseEntity<PublisherResponse> findById(@PathVariable UUID publisherId) {
        PublisherResponse publisher = publisherService.findById(publisherId);
        return ResponseEntity.ok(publisher);
    }

    @DeleteMapping("{publisherId}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID publisherId) {
        publisherService.deleteById(publisherId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> create(@RequestBody PublisherRequest request) {
        PublisherResponse publisher = publisherService.create(request);
        return ResponseEntity.created(URI.create("/api/publisher/" + publisher.publisherId())).body(publisher);
    }
}
