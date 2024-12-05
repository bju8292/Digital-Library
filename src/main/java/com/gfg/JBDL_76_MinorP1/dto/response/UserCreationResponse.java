package com.gfg.JBDL_76_MinorP1.dto.response; //data transfer object

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserCreationResponse {

    private String userName;

    private String userEmail;

    private String userAddress;

    private String userPhone;

}
