package com.example.securityprac.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "해당 이름이 이미 존재합니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 아이디가 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "");

    private HttpStatus status;
    private String message;
}
