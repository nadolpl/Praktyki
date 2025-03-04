package pl.sensilabs.praktyki.controllers;

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
import pl.sensilabs.praktyki.responses.BookTypeResponse;
import pl.sensilabs.praktyki.services.BookTypeService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Set;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BookTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BookTypeService bookTypeService;

    @Test
    void getBookTypeById() throws Exception {
        when(bookTypeService.getTypes()).thenReturn(Set.of(
                BookTypeResponse.builder().bookTypeName("horror").
                        bookTypeId(1).
                        build(),
                BookTypeResponse.builder().bookTypeId(2).
                        bookTypeName("bajka").
                        build(),
                BookTypeResponse.builder().
                        bookTypeName("przygodowy").
                        bookTypeId(3).
                        build()
        ));
        mockMvc.perform(get("/api/types", MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[2].bookTypeName", Matchers.is("przygodowy")));
    }
}
