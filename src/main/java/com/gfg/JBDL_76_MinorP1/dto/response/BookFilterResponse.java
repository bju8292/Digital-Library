package com.gfg.JBDL_76_MinorP1.dto.response;

import com.gfg.JBDL_76_MinorP1.enums.BookType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookFilterResponse {
    private String bookNo;
    private String bookName;
    private BookType bookType;
    private String authorName;
    private String authorEmail;
}
