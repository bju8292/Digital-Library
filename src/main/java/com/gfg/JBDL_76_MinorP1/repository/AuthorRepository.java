package com.gfg.JBDL_76_MinorP1.repository;

import com.gfg.JBDL_76_MinorP1.model.Author;
import com.gfg.JBDL_76_MinorP1.model.AuthorCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, AuthorCompositeKey> {

    Author findByEmail(String email);
}
