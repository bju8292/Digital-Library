package com.gfg.JBDL_76_MinorP1.service.impl;

import com.gfg.JBDL_76_MinorP1.dto.request.BookCreationRequest;
import com.gfg.JBDL_76_MinorP1.dto.response.BookCreationResponse;
import com.gfg.JBDL_76_MinorP1.enums.BookFilter;
import com.gfg.JBDL_76_MinorP1.enums.Operator;
import com.gfg.JBDL_76_MinorP1.model.Author;
import com.gfg.JBDL_76_MinorP1.model.Book;
import com.gfg.JBDL_76_MinorP1.model.User;
import com.gfg.JBDL_76_MinorP1.repository.BookRepository;
import com.gfg.JBDL_76_MinorP1.service.BookFilterFactory;
import com.gfg.JBDL_76_MinorP1.service.BookFilterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookFilterFactory bookFilterFactory;

    public BookCreationResponse addBook(BookCreationRequest request) {
      // author is already present or not?
      Author authorFromDb = authorService.findAuthorInDb(request.getAuthorEmail());
//      if(authorFromDb == null) {
//          authorFromDb = authorService.saveMyAuthor(Author.
//                  builder().
//                  id(UUID.randomUUID().toString()).
//                  email(request.getAuthorEmail()).
//                  name(request.getAuthorName()).
//                  build());
//      }
      Book book = request.toBook();
      if(authorFromDb == null) {
        authorFromDb = Author.builder().
                  id(UUID.randomUUID().toString()).
                  email(request.getAuthorEmail()).
                  name(request.getAuthorName()).
                  build();
      }
      book.setAuthor(authorFromDb);
      //book.setCreatedOn(new Date());
      book = bookRepository.save(book);
      return BookCreationResponse.
              builder().
              bookName(book.getTitle()).
              bookNo(book.getBookNo()).
              securityAmount(book.getSecurityAmount()).
              build();
    }

    public List<Book> filter(BookFilter filterBy, Operator operator, String value) {
        BookFilterStrategy stratergy = bookFilterFactory.getStratergy(filterBy);
        return stratergy.getFilteredBook(operator, value);
    }

    public Book checkIfBookIsValid(String bookNo) {
        List<Book> books = bookRepository.findByBookNo(bookNo);
        if(books.isEmpty()) {
            return null;
        }
        return books.get(0);
    }

    public void markBookUnavailable(Book bookFromDb, User userFromDb) {
        bookFromDb.setUser(userFromDb);
        bookRepository.save(bookFromDb);
    }
}
