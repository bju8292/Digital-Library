package com.gfg.JBDL_76_MinorP1.service.impl;

import com.gfg.JBDL_76_MinorP1.enums.Operator;
import com.gfg.JBDL_76_MinorP1.model.Book;
import com.gfg.JBDL_76_MinorP1.service.BookFilterStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookTypeFilterImpl implements BookFilterStrategy {
    @Override
    public List<Book> getFilteredBook(Operator operator, String value) {
        return List.of();
    }
}
