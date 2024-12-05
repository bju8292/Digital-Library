package com.gfg.JBDL_76_MinorP1.service;

import com.gfg.JBDL_76_MinorP1.enums.BookFilter;
import com.gfg.JBDL_76_MinorP1.service.impl.BookNoFilterImpl;
import com.gfg.JBDL_76_MinorP1.service.impl.BookTitleFilterImpl;
import com.gfg.JBDL_76_MinorP1.service.impl.BookTypeFilterImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookFilterFactory {
    private final Map<BookFilter, BookFilterStrategy> stratergies = new HashMap<>();

    public BookFilterFactory(BookNoFilterImpl bookNoFilter, BookTitleFilterImpl bookTitleFilter, BookTypeFilterImpl bookTypeFilter) {
        stratergies.put(BookFilter.BOOK_NO, bookNoFilter);
        stratergies.put(BookFilter.BOOK_TITLE, bookTitleFilter);
        stratergies.put(BookFilter.BOOK_TYPE, bookTypeFilter);
    }
    public BookFilterStrategy getStratergy(BookFilter filter) {
        return stratergies.get(filter);
    }

   /* public BookFilterStrategy getStrategy(BookFilter filterBy) {
        return null;
    }*/
}
