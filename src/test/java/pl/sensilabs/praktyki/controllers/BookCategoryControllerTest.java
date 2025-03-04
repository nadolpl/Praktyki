package pl.sensilabs.praktyki.controllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import pl.sensilabs.praktyki.responses.BookCategoryResponse;
import pl.sensilabs.praktyki.services.BookCategoryService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookCategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BookCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BookCategoryService bookCategoryService;

    @Test
    void testGetTypes() throws Exception {

        List<BookCategoryResponse> mockResponse = List.of(new BookCategoryResponse("Fiction"));
        when(bookCategoryService.getTypes()).thenReturn(mockResponse);


        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].categoryName").value("Fiction"));

    }

}
