package com.example.securityprac.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String email;
}
