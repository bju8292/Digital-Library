package com.gfg.JBDL_76_MinorP1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AuthorTest {

    @Test
    public void testGetEmail() {
        Author author = new Author();
        author.setEmail("a@gmail.com");
        assertEquals("a@gmail.com", author.getEmail());
    }
}
