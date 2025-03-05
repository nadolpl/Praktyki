package pl.sensilabs.praktyki.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import pl.sensilabs.praktyki.requests.AuthorRequest;
import pl.sensilabs.praktyki.responses.AuthorResponse;
import pl.sensilabs.praktyki.services.AuthorService;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    private static final UUID TEST_UUID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Kowalski";
    private static final String EMAIL = "jan@kowalski.com";

    @MockitoBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/authors - Should return 200 OK with authors list")
    void findAll_ShouldReturnAuthorsList() throws Exception {
        // Arrange
        AuthorResponse authorResponse = AuthorResponse.builder()
                .id(TEST_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .build();

        given(authorService.findAll()).willReturn(List.of(authorResponse));

        // Act & Assert
        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(TEST_UUID.toString()))
                .andExpect(jsonPath("$[0].firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$[0].lastName").value(LAST_NAME))
                .andExpect(jsonPath("$[0].email").value(EMAIL));
    }

    @Test
    @DisplayName("GET /api/authors/{id} - Should return 200 OK with author")
    void findById_ShouldReturnAuthor() throws Exception {
        // Arrange
        AuthorResponse authorResponse = AuthorResponse.builder()
                .id(TEST_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .build();

        given(authorService.findById(TEST_UUID)).willReturn(authorResponse);

        // Act & Assert
        mockMvc.perform(get("/api/authors/{id}", TEST_UUID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_UUID.toString()))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME))
                .andExpect(jsonPath("$.email").value(EMAIL));
    }

    @Test
    @DisplayName("POST /api/authors - Should return 201 CREATED with author")
    void create_ShouldReturnAuthor() throws Exception {
        // Arrange
        AuthorRequest authorRequest = new AuthorRequest(FIRST_NAME, LAST_NAME, EMAIL);
        AuthorResponse authorResponse = AuthorResponse.builder()
                .id(TEST_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .build();

        given(authorService.create(authorRequest)).willReturn(authorResponse);

        // Act & Assert
        mockMvc.perform(post("/api/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(authorResponse)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(TEST_UUID.toString()))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME))
                .andExpect(jsonPath("$.email").value(EMAIL));
    }

    @Test
    @DisplayName("DELETE /api/authors/{id} - Should return 204 No Content")
    void delete_ShouldReturnNoContent() throws Exception {
        // Arrange
        doNothing().when(authorService).deleteById(TEST_UUID);

        // Act & Assert
        mockMvc.perform(delete("/api/authors/{id}", TEST_UUID))
                .andExpect(status().isNoContent());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
