package com.gfg.JBDL_76_MinorP1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TxnRequest {

    @NotBlank(message = "user email must not be blank")
    private String userEmail;

    @NotBlank(message = "user bookNo must not be blank")
    private String bookNo;
}
