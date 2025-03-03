package pl.sensilabs.praktyki.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sensilabs.praktyki.responses.PublisherResponse;
import pl.sensilabs.praktyki.services.PublisherService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publishers")
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
}
