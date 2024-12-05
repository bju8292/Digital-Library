package com.gfg.JBDL_76_MinorP1.service.impl;

import com.gfg.JBDL_76_MinorP1.dto.response.BookFilterResponse;
import com.gfg.JBDL_76_MinorP1.enums.Operator;
import com.gfg.JBDL_76_MinorP1.model.Book;
import com.gfg.JBDL_76_MinorP1.repository.BookRepository;
import com.gfg.JBDL_76_MinorP1.service.BookFilterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookNoFilterImpl implements BookFilterStrategy {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getFilteredBook(Operator operator, String value) {
       if(!operator.equals(Operator.EQUALS)) {
           throw new IllegalArgumentException("Only Equals is expected with book no");
       }
       List<Book> books = bookRepository.findByBookNo(value);
       return books;
//       return books.
//               stream().
//               map(book ->convertToBookFilterResponse(book)).
//               collect(Collectors.toList());
    }

    private BookFilterResponse convertToBookFilterResponse(Book book) {
        return BookFilterResponse.
                builder().
                bookNo(book.getBookNo()).
                authorEmail(book.getAuthor().getEmail()).
                authorName(book.getAuthor().getName()).
                bookType(book.getBookType()).
                bookName(book.getTitle()).
                build();
    }
}
