package com.gfg.JBDL_76_MinorP1.service.impl;

import com.gfg.JBDL_76_MinorP1.model.Author;
import com.gfg.JBDL_76_MinorP1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findAuthorInDb(String authorEmail) {
        return authorRepository.findByEmail(authorEmail);
    }

    public Author saveMyAuthor(Author author) {
        return authorRepository.save(author);
    }
}
