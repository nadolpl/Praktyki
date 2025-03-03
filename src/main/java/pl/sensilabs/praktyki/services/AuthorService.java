package pl.sensilabs.praktyki.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sensilabs.praktyki.entities.Author;
import pl.sensilabs.praktyki.mappers.AuthorMapper;
import pl.sensilabs.praktyki.repositories.AuthorRepository;
import pl.sensilabs.praktyki.requests.AuthorRequest;
import pl.sensilabs.praktyki.responses.AuthorResponse;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Iterable<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper::toResponse)
                .collect(Collectors.toSet());
    }

    public AuthorResponse findById(UUID authorId) {
        return authorRepository.findById(authorId)
                .map(AuthorMapper::toResponse)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(UUID authorId) {
        if (authorRepository.existsById(authorId)) {
            authorRepository.deleteById(authorId);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public AuthorResponse create(AuthorRequest request) {
        return AuthorMapper.toResponse(authorRepository.save(AuthorMapper.toEntity(request)));
    }
}
