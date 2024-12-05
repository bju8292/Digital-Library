package com.gfg.JBDL_76_MinorP1.controller;

import com.gfg.JBDL_76_MinorP1.enums.BookType;
import com.gfg.JBDL_76_MinorP1.service.impl.BookService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testAddBook() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bookTitle", "title");
        jsonObject.put("securityAmount", 100.0);
        jsonObject.put("bookNo", "bookNo");
        jsonObject.put("bookType", "EDUCATIONAL");
        jsonObject.put("authorName", "authorName");
        jsonObject.put("authorEmail", "authorEmail");

        mockMvc.perform(post("/book/addBook").
                contentType(MediaType.APPLICATION_JSON_VALUE).
                content(jsonObject.toString())).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
