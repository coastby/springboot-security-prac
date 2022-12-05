package com.example.securityprac.dto;

import lombok.Getter;

@Getter
public class ReviewRequest {
    private Long hospitalId;
    private String content;
    private String userName;
}
