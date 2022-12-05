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
    public User toEntity(String password){  //비밀번호 암호화해서 매개변수로 넣어서 엔티티 생성
        return User.builder()
                .userName(this.userName)
                .password(password)
                .email(this.email)
                .role(UserRole.USER)    //role 추가
                .build();
    }
}
