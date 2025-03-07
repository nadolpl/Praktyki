package pl.sensilabs.praktyki.author;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorFacade {

    private final AuthorRepository authorRepository;

    Iterable<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper::toResponse)
                .collect(Collectors.toSet());
    }

    AuthorResponse findById(UUID authorId) {
        return authorRepository.findById(authorId)
                .map(AuthorMapper::toResponse)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

    @Transactional
    void deleteById(UUID authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new AuthorNotFoundException(authorId);
        }

        authorRepository.deleteById(authorId);
    }

    @Transactional
    AuthorResponse create(AuthorRequest request) {
        return AuthorMapper.toResponse(
                authorRepository.save(AuthorMapper.toEntity(request))
        );
    }

    public void validateAuthorsFromRequest(Set<UUID> authors) {
        var allAuthors = authorRepository.findAllBy().stream()
                .map(AuthorRepository.AuthorIdOnly::getId).toList();
        for (var author : authors) {
            if (!allAuthors.contains(author)) {
                throw new AuthorNotFoundException(author);
            }
        }
    }
}
