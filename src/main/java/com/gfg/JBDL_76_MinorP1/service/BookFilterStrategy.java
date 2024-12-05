package com.gfg.JBDL_76_MinorP1.service;

import com.gfg.JBDL_76_MinorP1.enums.Operator;
import com.gfg.JBDL_76_MinorP1.model.Book;

import java.util.List;

public interface BookFilterStrategy {
    List<Book>  getFilteredBook(Operator operator, String value);
}
