package com.gfg.JBDL_76_MinorP1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TxnreturnRequest {

    @NotBlank
    private String bookNo;

    @NotBlank
    private String txnId;

    @NotBlank
    private String userEmail; //but we should not ask this from user
}
