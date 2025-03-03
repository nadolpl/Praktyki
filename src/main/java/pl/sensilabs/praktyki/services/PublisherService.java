package pl.sensilabs.praktyki.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.mappers.PublisherMapper;
import pl.sensilabs.praktyki.repositories.PublisherRepository;
import pl.sensilabs.praktyki.responses.PublisherResponse;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PublisherService {

    private final PublisherRepository publisherRepository;

    public Iterable<PublisherResponse> findAll() {
        return publisherRepository.findAll()
                .stream()
                .map(PublisherMapper::toResponse)
                .collect(Collectors.toSet());
    }

    public PublisherResponse findById(UUID publisherId) {
        return publisherRepository.findById(publisherId)
                .map(PublisherMapper::toResponse)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(UUID publisherId) {
        if (publisherRepository.existsById(publisherId)) {
            publisherRepository.deleteById(publisherId);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
