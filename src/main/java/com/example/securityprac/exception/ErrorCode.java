package com.example.securityprac.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "해당 이름이 이미 존재합니다.");

    private HttpStatus status;
    private String message;
}
