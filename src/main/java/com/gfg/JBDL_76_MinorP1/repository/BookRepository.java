package com.gfg.JBDL_76_MinorP1.repository;

import com.gfg.JBDL_76_MinorP1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByBookNo(String bookNo);
}
