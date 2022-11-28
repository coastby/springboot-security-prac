package com.example.securityprac.dto;

import lombok.Getter;

@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;
}
