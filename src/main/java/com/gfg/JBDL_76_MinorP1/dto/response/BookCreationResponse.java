package com.gfg.JBDL_76_MinorP1.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookCreationResponse {
    private String bookNo;
    private String bookName;
    private Double securityAmount;

}
