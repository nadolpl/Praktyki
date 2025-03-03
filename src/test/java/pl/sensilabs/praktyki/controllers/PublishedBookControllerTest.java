package pl.sensilabs.praktyki.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sensilabs.praktyki.services.PublishedBookService;

@WebMvcTest(controllers = PublishedBookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PublishedBookControllerTest {

  @Autowired
  MockMvc mockMvc;
  @MockitoBean
  PublishedBookService publishedBookService;

  //testy
}
