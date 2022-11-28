package com.example.securityprac.dto;

import com.example.securityprac.domain.User;
import lombok.Getter;

@Getter
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
