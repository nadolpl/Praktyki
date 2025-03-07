package pl.sensilabs.praktyki.publisher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publishers")
class PublisherController {

    private final PublisherFacade publisherFacade;

    @GetMapping
    ResponseEntity<Iterable<PublisherResponse>> findAll() {
        return ResponseEntity.ok(publisherFacade.findAll());
    }

    @GetMapping("/{publisherId}")
    ResponseEntity<PublisherResponse> findById(@PathVariable UUID publisherId) {
        return ResponseEntity.ok(publisherFacade.findById(publisherId));
    }

    @DeleteMapping("/{publisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable UUID publisherId) {
        publisherFacade.deleteById(publisherId);
    }

    @PostMapping
    ResponseEntity<PublisherResponse> create(@RequestBody @Valid PublisherRequest request) {
        PublisherResponse publisher = publisherFacade.create(request);
        URI location = URI.create("/api/publishers/" + publisher.id());
        return ResponseEntity.created(location).body(publisher);
    }
}
