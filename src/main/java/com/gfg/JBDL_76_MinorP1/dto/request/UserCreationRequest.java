package com.gfg.JBDL_76_MinorP1.dto.request;

import com.gfg.JBDL_76_MinorP1.model.User;
import com.gfg.JBDL_76_MinorP1.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserCreationRequest {

    private String userName;

    @NotBlank(message = "user email must not be blank")
    private String userEmail;

    private String userAddress;

    private String userPhone;


    public User toUser() {
        return User.builder().
                name(this.userName).
                email(this.userEmail).
                phoneNo(this.userPhone).
                address(this.userAddress).
                userstatus(UserStatus.ACTIVE).
                build();
    }
}
