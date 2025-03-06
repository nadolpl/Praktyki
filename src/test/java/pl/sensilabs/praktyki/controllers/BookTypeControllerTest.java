package pl.sensilabs.praktyki.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sensilabs.praktyki.entities.PublishedBook;
import pl.sensilabs.praktyki.services.BookTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookTypeController.class)
public class BookTypeControllerTest {

    private static  final Integer ID = 1;

    @MockitoBean
    private BookTypeService bookTypeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deleteShouldReturnNoContent() throws Exception {

        doNothing().when(bookTypeService).deleteTypeById(ID);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/types/delete/{id}", ID))
                .andExpect(status().isNoContent());
    }

}
