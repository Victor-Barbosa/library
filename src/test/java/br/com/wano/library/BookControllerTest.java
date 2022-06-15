package br.com.wano.library;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSaveBook() throws Exception {
        BookRequest bookRequest = new BookRequest("Harry Potter", new BigDecimal("70.0"),"98745643216");
        Book bookResponse = new Book(1L, bookRequest.titulo(), bookRequest.preco(), bookRequest.isbn());
        BDDMockito.given(bookService.save(bookRequest)).willReturn(bookResponse);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/books")
                .content(new Gson().toJson(bookRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(bookResponse)));

    }

    @Test
    void findBook() {
    }

    @Test
    void bookService() {
    }
}