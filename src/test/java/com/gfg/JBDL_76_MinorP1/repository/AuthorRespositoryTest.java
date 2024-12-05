package com.gfg.JBDL_76_MinorP1.repository;

import com.gfg.JBDL_76_MinorP1.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        //"spring.datasource.username=root",//no username & password for h2-in memory
        // "spring.datasource.password=Mys@8292700560"
})
public class AuthorRespositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setup() {
        Author a1 = Author.builder().id("1").email("a@gmail.com").name("a1").build();
        authorRepository.save(a1);
        Author a2 = Author.builder().id("2").email("b@gmail.com").name("a2").build();
        authorRepository.save(a2);
    }
    @Test
    public void testFindByEmail() {
        Author author = authorRepository.findByEmail("a@gmail.com");
        assertEquals(author.getName(), "a1");
    }
}
