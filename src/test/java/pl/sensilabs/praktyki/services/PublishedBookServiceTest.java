package pl.sensilabs.praktyki.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sensilabs.praktyki.repositories.PublishedBookRepository;

@ExtendWith(MockitoExtension.class)
public class PublishedBookServiceTest {

  @Mock
  PublishedBookRepository publishedBookRepository;
  @InjectMocks
  PublishedBookService publishedBookService;

  //testy
}
