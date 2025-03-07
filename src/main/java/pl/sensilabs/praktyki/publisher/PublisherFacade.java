package pl.sensilabs.praktyki.publisher;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherFacade {

    private final PublisherRepository publisherRepository;

    Iterable<PublisherResponse> findAll() {
        return publisherRepository.findAll()
                .stream()
                .map(PublisherMapper::toResponse)
                .collect(Collectors.toSet());
    }

    PublisherResponse findById(UUID publisherId) {
        return publisherRepository.findById(publisherId)
                .map(PublisherMapper::toResponse)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));
    }

    @Transactional
    void deleteById(UUID publisherId) {
        if (!publisherRepository.existsById(publisherId)) {
            throw new PublisherNotFoundException(publisherId);
        }

        publisherRepository.deleteById(publisherId);
    }

    public boolean publisherExistsById(UUID publisherId) {
        return publisherRepository.existsById(publisherId);
    }

    @Transactional
    PublisherResponse create(PublisherRequest request) {
        return PublisherMapper.toResponse(publisherRepository.save(PublisherMapper.toEntity(request)));
    }
}
