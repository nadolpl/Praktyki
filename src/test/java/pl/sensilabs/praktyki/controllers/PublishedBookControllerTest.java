package pl.sensilabs.praktyki.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sensilabs.praktyki.responses.PublishedBookResponse;
import pl.sensilabs.praktyki.services.PublishedBookService;

@WebMvcTest(controllers = PublishedBookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PublishedBookControllerTest {

  @Autowired
  MockMvc mockMvc;
  @MockitoBean
  PublishedBookService publishedBookService;

  @Test
  void getAllPublishedBooks() throws Exception {
    when(publishedBookService.getAllPublishedBooks()).thenReturn(List.of(
        PublishedBookResponse.builder()
            .publishedBookId(UUID.randomUUID())
            .bookTitle("Clean Code")
            .authors(new HashSet<>(List.of("Robert C. Martin")))
            .publisherName("PWN")
            .bookTypeName("E-book")
            .releaseNumber(3)
            .publishDate(LocalDate.now())
            .build(),
        PublishedBookResponse.builder()
            .publishedBookId(UUID.randomUUID())
            .bookTitle("Witcher")
            .authors(new HashSet<>(List.of("Jerzy Sapkowski")))
            .publisherName("Helion")
            .bookTypeName("Soft-cover")
            .releaseNumber(2)
            .publishDate(LocalDate.now())
            .build()
    ));

    mockMvc.perform(get("/api/published-books", MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(2)))
        .andExpect(jsonPath("$[0].bookTitle", Matchers.is("Clean Code")))
        .andExpect(jsonPath("$[1].bookTitle", Matchers.is("Witcher")));
  }
}
