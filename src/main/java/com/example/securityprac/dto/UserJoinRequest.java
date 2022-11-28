package com.example.securityprac.dto;

import com.example.securityprac.domain.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;
    public User toEntity(){
        return User.builder()
                .userName(this.userName)
                .password(this.password)
                .email(this.email)
                .build();
    }
}
